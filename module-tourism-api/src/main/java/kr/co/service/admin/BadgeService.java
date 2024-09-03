package kr.co.service.admin;

import kr.co.dto.BadgeCodeResDto;
import kr.co.entity.BadgeCode;
import kr.co.repository.BadgeCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeCodeRepository badgeCodeRepository;


    @Transactional(readOnly = true)
    public List<BadgeCodeResDto> getBadgeList() {
        List<BadgeCode> badgeCodes = badgeCodeRepository.findAll();
        return badgeCodes.stream()
                .map(badgeCode -> BadgeCodeResDto.builder()
                        .badgeCode(badgeCode.getBadgeCode())
                        .badgeCodeType(badgeCode.getBadgeCodeType())
                        .build())
                .collect(Collectors.toList());
    }
}
