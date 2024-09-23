package kr.co.service.admin;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.*;
import kr.co.dto.app.common.ServiceAdminUser;
import kr.co.entity.*;
import kr.co.repository.*;
import kr.co.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalItemService {

    private final AdminUserRepository adminUserRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    private final LocalItemRepository localItemRepository;
    private final FileGroupRepository fileGroupRepository;
    private final FileRepository fileRepository;
    private final AreaCodeRepository areaCodeRepository;
    private final DetailAreaCodeRepository detailAreaCodeRepository;

    @Transactional(readOnly = true)
    public Page<LocalItemResDto> getLocalItemList(int page, int size) {
        Page<LocalItem> localSpecialties = localItemRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<LocalItemResDto> localItemResDtos = localSpecialties.stream()
                .map(localItemDto -> LocalItemResDto.builder()
                        .localItemId(localItemDto.getLocalItemId())
                        .localItemName(localItemDto.getLocalItemName())
                        .localItemPrice(localItemDto.getLocalItemPrice())
                        .areaCodeId(localItemDto.getAreaCode().getAreaCodeId())
                        .detailAreaCodeId(localItemDto.getDetailAreaCode().getDetailAreaCodeId())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(localItemResDtos, localSpecialties.getPageable(), localSpecialties.getTotalElements());
    }

    public LocalItemDetailResDto getLocalItemDetail(String localItemId) {
        LocalItem localItem = localItemRepository.findById(localItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getCode(), CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getMessage()));

        return new LocalItemDetailResDto(localItem);
    }

    public void uploadLocalItem(LocalItemUploadDataReqDto localItemUploadDataReqDto, ServiceAdminUser serviceAdminUser) {
        AdminUser adminUser = adminUserRepository.findByAdminUserEmail(serviceAdminUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ADMIN_USER.getCode(), CommonErrorCode.NOT_FOUND_ADMIN_USER.getMessage()));

        localItemUploadDataReqDto.setAreaCodeId(adminUser.getDetailAreaCode().getAreaCode().getAreaCodeId());
        localItemUploadDataReqDto.setDetailAreaCodeId(adminUser.getDetailAreaCode().getDetailAreaCodeId());


        List<MultipartFile> images = localItemUploadDataReqDto.getLocalItemImages();
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }

        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(images, uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));

        AreaCode areaCode = areaCodeRepository.findById(localItemUploadDataReqDto.getAreaCodeId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_AREA_CODE.getMessage()));

        DetailAreaCode detailAreaCode = detailAreaCodeRepository.findById(localItemUploadDataReqDto.getDetailAreaCodeId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getMessage()));

        //특산품 정보 저장
        localItemRepository.save(new LocalItem(localItemUploadDataReqDto, areaCode, detailAreaCode, fileGroup));
    }

    @Transactional
    public void deleteLocalItem(String localItemId) {
        LocalItem localItem = localItemRepository.findById(localItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_FOUND_LOCAL_ITEM.getMessage()));
        localItem.deleteLocalItem();
    }

    @Transactional
    public void updateLocalItem(String localItemId, LocalItemUpdateReqDto localItemUpdateReqDto, ServiceAdminUser serviceAdminUser) {
        AdminUser adminUser = adminUserRepository.findByAdminUserEmail(serviceAdminUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ADMIN_USER.getCode(), CommonErrorCode.NOT_FOUND_ADMIN_USER.getMessage()));

        localItemUpdateReqDto.setAreaCodeId(adminUser.getDetailAreaCode().getAreaCode().getAreaCodeId());
        localItemUpdateReqDto.setDetailAreaCodeId(adminUser.getDetailAreaCode().getDetailAreaCodeId());

        LocalItem localItem = localItemRepository.findById(localItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_LOCAL_ITEM.getCode(), CommonErrorCode.NOT_FOUND_LOCAL_ITEM.getMessage()));

        List<MultipartFile> images = localItemUpdateReqDto.getLocalItemImages();
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }

        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(images, uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));

        AreaCode areaCode = areaCodeRepository.findById(localItemUpdateReqDto.getAreaCodeId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_AREA_CODE.getMessage()));

        DetailAreaCode detailAreaCode = detailAreaCodeRepository.findById(localItemUpdateReqDto.getDetailAreaCodeId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getMessage()));

        localItem.updateLocalItem(localItemUpdateReqDto, areaCode, detailAreaCode, fileGroup);
    }

    @Transactional
    public void deleteMultiLocalItem(List<String> localItemIds) {
        localItemIds.forEach(this::deleteLocalItem);
    }
}
