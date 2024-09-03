package kr.co.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.FileSaveDto;
import kr.co.dto.LocalItemDetailResDto;
import kr.co.dto.LocalItemResDto;
import kr.co.dto.LocalItemUploadReqDto;
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
                        .localSpecialtyId(localItemDto.getLocalItemId())
                        .localSpecialtyName(localItemDto.getLocalItemName())
                        .localSpecialtyPrice(localItemDto.getLocalItemPrice() + "원")
                        .localSpecialtyStampCount(localItemDto.getLocalItemBadgeCount() + "개")
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(localItemResDtos, localSpecialties.getPageable(), localSpecialties.getTotalElements());
    }

    public LocalItemDetailResDto getLocalItemDetail(String localSpecialtyId) {
        LocalItem localItem = localItemRepository.findById(localSpecialtyId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getCode(), CommonErrorCode.NOT_FOUND_LOCAL_SPECIALTY.getMessage()));

        return new LocalItemDetailResDto(localItem);
    }

    public void uploadLocalItem(LocalItemUploadReqDto localItemUploadReqDto, List<MultipartFile> localItemImages) {
        List<MultipartFile> images = localItemImages;
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }

        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(localItemImages, uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));

        AreaCode areaCode = areaCodeRepository.findById(localItemUploadReqDto.getAreaCode())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_AREA_CODE.getMessage()));

        DetailAreaCode detailAreaCode = detailAreaCodeRepository.findById(localItemUploadReqDto.getDetailAreaCode())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_DETAIL_AREA_CODE.getMessage()));

        //특산품 정보 저장
        localItemRepository.save(new LocalItem(localItemUploadReqDto,areaCode,detailAreaCode, fileGroup));
    }
}
