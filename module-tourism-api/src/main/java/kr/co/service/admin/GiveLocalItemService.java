package kr.co.service.admin;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.GiveLocalItemReqDto;
import kr.co.dto.GiveLocalItemResDto;
import kr.co.dto.app.common.ServiceAdminUser;
import kr.co.entity.BadgeCode;
import kr.co.entity.GiveLocalItem;
import kr.co.repository.BadgeCodeRepository;
import kr.co.repository.GiveLocalItemRepository;
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
public class GiveLocalItemService {

    private final GiveLocalItemRepository giveLocalItemRepository;
    private final BadgeCodeRepository badgeCodeRepository;

    @Transactional
    public void saveLocalItem(GiveLocalItemReqDto giveLocalItemReqDto, ServiceAdminUser serviceAdminUser) {
        BadgeCode badgeCode = badgeCodeRepository.findById(giveLocalItemReqDto.getBadgeCode())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
        giveLocalItemRepository.save(new GiveLocalItem(giveLocalItemReqDto, badgeCode, serviceAdminUser.getUserEmail()));
    }

    //test
    @Transactional(readOnly = true)
    public Page<GiveLocalItemResDto> getGiveLocalItemList(ServiceAdminUser serviceAdminUser,int page, int size) throws Exception {
        log.info("serviceAdminUser : {}", serviceAdminUser.getUserEmail());
        Page<GiveLocalItem> giveLocalItems = giveLocalItemRepository.findAllByRegUserEmail(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")), serviceAdminUser.getUserEmail());
        List<GiveLocalItemResDto> giveLocalItemResDtos = giveLocalItems.stream()
                .map(giveLocalItem -> GiveLocalItemResDto.builder()
                        .giveLocalItemId(giveLocalItem.getGiveLocalItemId())
                        .giveLocalItemName(giveLocalItem.getGiveLocalItemName())
                        .giveLocalItemPrice(giveLocalItem.getGiveLocalItemPrice())
                        .badgeCodeName(giveLocalItem.getBadgeCode().getBadgeCodeType())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(giveLocalItemResDtos, giveLocalItems.getPageable(), giveLocalItems.getTotalElements());
    }

    @Transactional
    public void updateLocalItem(String giveLocalItemId, GiveLocalItemReqDto giveLocalItemReqDto) {
        GiveLocalItem giveLocalItem = giveLocalItemRepository.findById(giveLocalItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getMessage()));

        BadgeCode badgeCode = badgeCodeRepository.findById(giveLocalItemReqDto.getBadgeCode())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));

        giveLocalItem.updateGiveLocalItem(giveLocalItemReqDto, badgeCode);
    }

    @Transactional
    public void deleteGiveLocalItem(String giveLocalItemId) {
        GiveLocalItem giveLocalItem = giveLocalItemRepository.findById(giveLocalItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getMessage()));

        giveLocalItem.deleteGiveLocalItem();
    }

    @Transactional(readOnly = true)
    public GiveLocalItemResDto getGiveLocalItemDetail(String giveLocalItemId) {
        GiveLocalItem giveLocalItem = giveLocalItemRepository.findById(giveLocalItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getMessage()));

        return GiveLocalItemResDto.builder()
                .giveLocalItemName(giveLocalItem.getGiveLocalItemName())
                .giveLocalItemPrice(giveLocalItem.getGiveLocalItemPrice())
                .badgeCodeName(giveLocalItem.getBadgeCode().getBadgeCode())
                .build();
    }

    @Transactional
    public void deleteMultiLocalItem(List<String> giveLocalItemIds) {
        giveLocalItemIds.forEach(this::deleteGiveLocalItem);

    }
}
