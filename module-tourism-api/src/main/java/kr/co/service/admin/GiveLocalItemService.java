package kr.co.service.admin;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.GiveLocalItemDetailResDto;
import kr.co.dto.GiveLocalItemNameResDto;
import kr.co.dto.GiveLocalItemReqDto;
import kr.co.dto.GiveLocalItemResDto;
import kr.co.dto.app.common.ServiceAdminUser;
import kr.co.entity.AdminUser;
import kr.co.entity.BadgeCode;
import kr.co.entity.DetailAreaCode;
import kr.co.entity.GiveLocalItem;
import kr.co.repository.AdminUserRepository;
import kr.co.repository.BadgeCodeRepository;
import kr.co.repository.DetailAreaCodeRepository;
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
    private final AdminUserRepository adminUserRepository;
    private final DetailAreaCodeRepository detailAreaCodeRepository;

    @Transactional
    public void saveLocalItem(GiveLocalItemReqDto giveLocalItemReqDto, ServiceAdminUser serviceAdminUser) {
        AdminUser adminUser = adminUserRepository.findByAdminUserEmail(serviceAdminUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ADMIN_USER.getCode(), CommonErrorCode.NOT_FOUND_ADMIN_USER.getMessage()));

        DetailAreaCode detailAreaCode = detailAreaCodeRepository.findById(adminUser.getDetailAreaCode().getDetailAreaCodeId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getMessage()));

        switch (detailAreaCode.getName()) {
            case "중구" -> {
                BadgeCode badgeCode = badgeCodeRepository.findById("97")
                        .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
                giveLocalItemRepository.save(new GiveLocalItem(giveLocalItemReqDto, badgeCode, serviceAdminUser.getUserEmail()));
            }
            case "해운대구" -> {
                BadgeCode badgeCode = badgeCodeRepository.findById("98")
                        .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
                giveLocalItemRepository.save(new GiveLocalItem(giveLocalItemReqDto, badgeCode, serviceAdminUser.getUserEmail()));
            }
            case "속초시" -> {
                BadgeCode badgeCode = badgeCodeRepository.findById("99")
                        .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
                giveLocalItemRepository.save(new GiveLocalItem(giveLocalItemReqDto, badgeCode, serviceAdminUser.getUserEmail()));
            }
        }
    }

    @Transactional(readOnly = true)
    public Page<GiveLocalItemResDto> getGiveLocalItemList(ServiceAdminUser serviceAdminUser, int page, int size) throws Exception {
        log.info("serviceAdminUser : {}", serviceAdminUser.getUserEmail());
        Page<GiveLocalItem> giveLocalItems = giveLocalItemRepository.findAllByRegUserEmailAndDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")), serviceAdminUser.getUserEmail());
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
    public void updateLocalItem(String giveLocalItemId, GiveLocalItemReqDto giveLocalItemReqDto, ServiceAdminUser serviceAdminUser) {
        AdminUser adminUser = adminUserRepository.findByAdminUserEmail(serviceAdminUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ADMIN_USER.getCode(), CommonErrorCode.NOT_FOUND_ADMIN_USER.getMessage()));

        GiveLocalItem giveLocalItem = giveLocalItemRepository.findById(giveLocalItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getMessage()));

        DetailAreaCode detailAreaCode = detailAreaCodeRepository.findById(adminUser.getDetailAreaCode().getDetailAreaCodeId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getMessage()));

        switch (detailAreaCode.getName()) {
            case "중구" -> {
                BadgeCode badgeCode = badgeCodeRepository.findById("97")
                        .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
                giveLocalItem.updateGiveLocalItem(giveLocalItemReqDto, badgeCode);
            }
            case "해운대구" -> {
                BadgeCode badgeCode = badgeCodeRepository.findById("98")
                        .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
                giveLocalItem.updateGiveLocalItem(giveLocalItemReqDto, badgeCode);
            }
            case "속초시" -> {
                BadgeCode badgeCode = badgeCodeRepository.findById("99")
                        .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));
                giveLocalItem.updateGiveLocalItem(giveLocalItemReqDto, badgeCode);
            }
        }
    }

    @Transactional
    public void deleteGiveLocalItem(String giveLocalItemId) {
        GiveLocalItem giveLocalItem = giveLocalItemRepository.findById(giveLocalItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getMessage()));

        giveLocalItem.deleteGiveLocalItem();
    }

    @Transactional(readOnly = true)
    public GiveLocalItemDetailResDto getGiveLocalItemDetail(String giveLocalItemId) {
        GiveLocalItem giveLocalItem = giveLocalItemRepository.findById(giveLocalItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_EXIST_GIVE_LOCAL_ITEM.getMessage()));

        return GiveLocalItemDetailResDto.builder()
                .giveLocalItemName(giveLocalItem.getGiveLocalItemName())
                .giveLocalItemPrice(giveLocalItem.getGiveLocalItemPrice())
                .specialBadgeCodeName(giveLocalItem.getSpecialBadgeCodeName())
                .build();
    }

    @Transactional
    public void deleteMultiLocalItem(List<String> giveLocalItemIds) {
        giveLocalItemIds.forEach(this::deleteGiveLocalItem);

    }

    @Transactional(readOnly = true)
    public List<String> getGiveLocalItemName() {
        return giveLocalItemRepository.findAllByDelYnFalse().stream()
                .map(GiveLocalItem::getGiveLocalItemName)
                .collect(Collectors.toList());
    }
}
