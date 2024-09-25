package kr.co.service.app;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.qr.QrReqDto;
import kr.co.entity.TourismApi;
import kr.co.entity.TourismVisit;
import kr.co.entity.User;
import kr.co.entity.UserBadge;
import kr.co.repository.TourismApiRepository;
import kr.co.repository.TourismVisitRepository;
import kr.co.repository.UserBadgeRepository;
import kr.co.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class QrService {

    private final UserBadgeRepository userBadgeRepository;
    private final TourismApiRepository tourismApiRepository;
    private final UserRepository userRepository;
    private final TourismVisitRepository tourismVisitRepository;

    @Transactional
    public void receiveQrData(QrReqDto qrReqDto, ServiceUser serviceUser) {
        //qr 유효기간 체크
        String timeStr = qrReqDto.getQrTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(timeStr, formatter);

        // 현재 시간 구하기
        LocalDateTime now = LocalDateTime.now();

        // 30초 전 시간 구하기
        LocalDateTime nowMinus30Seconds = now.minusSeconds(30);

        if (dateTime.isBefore(nowMinus30Seconds)) {
            throw new CommonException(CommonErrorCode.ERROR_QR.getCode(), CommonErrorCode.ERROR_QR.getMessage());
        }

        TourismApi tourismApi = tourismApiRepository.findById(qrReqDto.getTourismApiId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        User user = userRepository.findById(serviceUser.getUserId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        userBadgeRepository.save(new UserBadge(user, tourismApi));
        tourismVisitRepository.save(new TourismVisit(user, tourismApi));
    }
}
