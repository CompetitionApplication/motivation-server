package kr.co.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.LocalSpecialtyDetailResDto;
import kr.co.dto.LocalSpecialtyResDto;
import kr.co.entity.LocalItem;
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
        Page<LocalItem> localSpecialties = localSpecialtyRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<LocalSpecialtyResDto> localSpecialtyResDtos = localSpecialties.stream()
                .map(localItemDto -> LocalSpecialtyResDto.builder()
                        .localSpecialtyId(localItemDto.getLocalItemId())
                        .localSpecialtyName(localItemDto.getLocalItemName())
                        .localSpecialtyPrice(localItemDto.getLocalItemPrice() + "원")
                        .localSpecialtyStampCount(localItemDto.getLocalItemStampCount() + "개")
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(localSpecialtyResDtos, localSpecialties.getPageable(), localSpecialties.getTotalElements());
    }

    public Object getLocalSpecialtyDetail(String localSpecialtyId) {
        LocalItem localItem = localSpecialtyRepository.findById(localSpecialtyId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getCode(), CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getMessage()));

        return new LocalSpecialtyDetailResDto(localItem);
    }
}
