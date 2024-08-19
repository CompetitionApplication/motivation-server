package kr.co.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.LocalSpecialtyDetailResDto;
import kr.co.dto.LocalSpecialtyResDto;
import kr.co.entity.LocalSpecialty;
import kr.co.repository.LocalSpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalSpecialtyService {

    private final LocalSpecialtyRepository localSpecialtyRepository;

    @Transactional(readOnly = true)
    public Page<LocalSpecialtyResDto> getLocalSpecialtyList(int page, int size) {
        Page<LocalSpecialty> localSpecialties = localSpecialtyRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<LocalSpecialtyResDto> localSpecialtyResDtos = localSpecialties.stream()
                .map(localSpecialtyDto -> LocalSpecialtyResDto.builder()
                        .localSpecialtyId(localSpecialtyDto.getLocalSpecialtyId())
                        .localSpecialtyName(localSpecialtyDto.getLocalSpecialtyName())
                        .localSpecialtyPrice(localSpecialtyDto.getLocalSpecialtyPrice() + "원")
                        .localSpecialtyStampCount(String.valueOf(localSpecialtyDto.getLocalSpecialtyStampCount()) + "개")
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(localSpecialtyResDtos, localSpecialties.getPageable(), localSpecialties.getTotalElements());
    }

    public Object getLocalSpecialtyDetail(String localSpecialtyId) {
        LocalSpecialty localSpecialty = localSpecialtyRepository.findById(localSpecialtyId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getCode(), CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getMessage()));

        return new LocalSpecialtyDetailResDto(localSpecialty);
    }
}
