package kr.co.service.app;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.qr.QrReqDto;
import kr.co.entity.TourismApi;
import kr.co.entity.User;
import kr.co.entity.UserBadge;
import kr.co.repository.TourismApiRepository;
import kr.co.repository.UserBadgeRepository;
import kr.co.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QrService {

    private final UserBadgeRepository userBadgeRepository;
    private final TourismApiRepository tourismApiRepository;
    private final UserRepository userRepository;

    @Transactional
    public void receiveQrData(QrReqDto qrReqDto) {
        TourismApi tourismApi = tourismApiRepository.findById(qrReqDto.getTourismApiId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        User user = userRepository.findById(qrReqDto.getUserId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        userBadgeRepository.save(new UserBadge(user, tourismApi));
    }
}