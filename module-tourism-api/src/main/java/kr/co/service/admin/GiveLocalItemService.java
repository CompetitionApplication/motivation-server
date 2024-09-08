package kr.co.service.admin;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.GiveLocalItemReqDto;
import kr.co.dto.GiveLocalItemResDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.entity.BadgeCode;
import kr.co.entity.GiveLocalItem;
import kr.co.repository.BadgeCodeRepository;
import kr.co.repository.GiveLocalItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiveLocalItemService {

    private final GiveLocalItemRepository giveLocalItemRepository;
    private final BadgeCodeRepository badgeCodeRepository;

    @Transactional
    public void saveLocalItem(GiveLocalItemReqDto giveLocalItemReqDto, ServiceUser serviceUser) {
        BadgeCode badgeCode = badgeCodeRepository.findById(giveLocalItemReqDto.getBadgeCode())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
        giveLocalItemRepository.save(new GiveLocalItem(giveLocalItemReqDto, badgeCode, serviceUser.getUserEmail()));
    }


    public List<GiveLocalItemResDto> getGiveLocalItemList(ServiceUser serviceUser) {
        List<GiveLocalItem> giveLocalItems = giveLocalItemRepository.findAllByRegUserEmail(serviceUser.getUserEmail());
        return giveLocalItems.stream()
                .map(giveLocalItem -> GiveLocalItemResDto.builder()
                        .giveLocalItemName(giveLocalItem.getGiveLocalItemName())
                        .giveLocalItemPrice(giveLocalItem.getGiveLocalItemPrice())
                        .badgeCode(giveLocalItem.getBadgeCode().getBadgeCode())
                        .build())
                .collect(Collectors.toList());
    }
}
