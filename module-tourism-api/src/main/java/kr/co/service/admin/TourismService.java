package kr.co.service.admin;


import kr.co.auth.AdminLoginUser;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TourismService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.base-url}")
    private String baseUrl;

    private final FileRepository fileRepository;
    private final FileGroupRepository fileGroupRepository;
    private final TourismApiRepository tourismApiRepository;
    private final AreaCodeRepository areaCodeRepository;
    private final DetailAreaCodeRepository detailAreaCodeRepository;
    private final BadgeCodeRepository badgeCodeRepository;
    private final AdminUserRepository adminUserRepository;

    @Transactional(readOnly = true)
    public Page<TourPlaceResDto> getTourismList(int page, int size, ServiceAdminUser serviceAdminUser) {
        // 관광지 외부 API DATA (한국어 버전만 추출)
        AdminUser adminUser = adminUserRepository.findByAdminUserEmail(serviceAdminUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ADMIN_USER.getCode(), CommonErrorCode.NOT_FOUND_ADMIN_USER.getMessage()));


        List<TourismApi> tourismApis = tourismApiRepository.findAllByDelYnFalseAndCountryAndAreacode("KOR", adminUser.getDetailAreaCode().getAreaCode().getCode());

        // 외부 API 데이터를 DTO로 변환하여 추가
        List<TourPlaceResDto> tourismApiDtos = tourismApis.stream()
                .map(api -> TourPlaceResDto.builder()
                        .tourPlaceId(api.getTourismApiId())
                        .tourPlaceName(api.getTitle())
                        .tourPlaceAddress(api.getAddr1() + " " + api.getAddr2())
                        .tourPlaceLink("")
                        .tourPlaceContact(api.getTel())
                        .badgeCodeName(api.getBadgeCode().getBadgeCodeType())
                        .build())
                .collect(Collectors.toList());

        return new PageImpl<>(tourismApiDtos, PageRequest.of(page, size), tourismApiDtos.size());
    }

    public List<AreaCodeResDto> areaCodeList() {
        List<AreaCode> areaCodes = areaCodeRepository.findAllByDelYnFalseAndCountry("KOR");

        return areaCodes.stream()
                .map(areaCode -> AreaCodeResDto.builder()
                        .areaCodeId(areaCode.getAreaCodeId())
                        .areaCode(areaCode.getCode())
                        .areaCodeName(areaCode.getName())
                        .build())
                .toList();
    }

    public List<DetailAreaCodeResDto> detailAreaCodeList(String areaCodeId) {
        AreaCode areaCode = areaCodeRepository.findById(areaCodeId).orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_AREA_CODE.getCode(), CommonErrorCode.NOT_FOUND_AREA_CODE.getMessage()));
        return detailAreaCodeRepository.findAllByDelYnFalseAndAreaCodeAndCountry(areaCode, "KOR").stream()
                .map(detailAreaCode -> DetailAreaCodeResDto.builder()
                        .detailAreaCodeId(detailAreaCode.getDetailAreaCodeId())
                        .detailAreaCode(detailAreaCode.getCode())
                        .detailAreaCodeName(detailAreaCode.getName())
                        .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public TourismApiDetailResDto getTourismDetail(String tourPlaceId) {
        TourismApi tourismApi = tourismApiRepository.findById(tourPlaceId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        List<String> tourismImages = new ArrayList<>();
        if (tourismApi.getFileGroup() != null) {
            tourismImages = tourismApi.getFileGroup().getFiles().stream()
                    .map(file -> file.getFilePath())
                    .collect(Collectors.toList());
        }
        return new TourismApiDetailResDto(tourismApi, tourismImages);
    }

    @Transactional
    public void deleteTourism(String tourismId) {
        TourismApi tourismApi = tourismApiRepository.findById(tourismId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        tourismApi.deleteTourism();
    }

    @Transactional
    public void uploadTourPlace(TourismUploadReqDto tourismUploadReqDto, ServiceAdminUser serviceAdminUser) {
        log.info("tourismUploadReqDto : {}", tourismUploadReqDto);
        List<MultipartFile> images = tourismUploadReqDto.getTourismImages();
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }
        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(images, uploadDir, baseUrl);
        List<File> savedFiles = fileRepository.saveAll(
                fileSaveDtos.stream()
                        .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                        .collect(Collectors.toList())
        );

        savedFiles.forEach(file -> {
            String fileUrl = file.getFileUrl() + file.getFileId();
            file.saveFileUrl(fileUrl);
        });

        // 3. 다시 업데이트 (한 번에 saveAll로 업데이트 가능)
        fileRepository.saveAll(savedFiles);
        /*fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));*/

        BadgeCode badgeCode = badgeCodeRepository.findById(tourismUploadReqDto.getBadgeCode())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));

        AdminUser adminUser = adminUserRepository.findByAdminUserEmail(serviceAdminUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ADMIN_USER.getCode(), CommonErrorCode.NOT_FOUND_ADMIN_USER.getMessage()));

        String areaCode = adminUser.getDetailAreaCode().getAreaCode().getCode();
        String detailCode = adminUser.getDetailAreaCode().getCode();
        //관광지 정보 저장
        tourismApiRepository.save(new TourismApi(tourismUploadReqDto, fileGroup, badgeCode, areaCode, detailCode));
    }

    @Transactional
    public void updateTourism(String tourismId, TourismUpdateReqDto tourismUpdateReqDto, ServiceAdminUser serviceAdminUser) {
        TourismApi tourismApi = tourismApiRepository.findById(tourismId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        //기존 파일 삭제
        FileGroup fileGroup = fileGroupRepository.findById(tourismApi.getFileGroup().getFileGroupId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_FILE_GROUP.getCode(), CommonErrorCode.NOT_EXIST_FILE_GROUP.getMessage()));
        fileGroup.deleteFileGroup(true);
        //파일 업로드, 파일 그룹 저장
        FileGroup newFileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(tourismUpdateReqDto.getTourismImages(), uploadDir, baseUrl);
        List<File> savedFiles = fileRepository.saveAll(
                fileSaveDtos.stream()
                        .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                        .collect(Collectors.toList())
        );

        savedFiles.forEach(file -> {
            String fileUrl = file.getFileUrl() + file.getFileId();
            file.saveFileUrl(fileUrl);
        });

        // 3. 다시 업데이트 (한 번에 saveAll로 업데이트 가능)
        fileRepository.saveAll(savedFiles);

/*        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, newFileGroup))
                .collect(Collectors.toList()));*/

        BadgeCode badgeCode = badgeCodeRepository.findById(tourismUpdateReqDto.getBadgeCode())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_BADGE_CODE.getCode(), CommonErrorCode.NOT_EXIST_BADGE_CODE.getMessage()));

        AdminUser adminUser = adminUserRepository.findByAdminUserEmail(serviceAdminUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ADMIN_USER.getCode(), CommonErrorCode.NOT_FOUND_ADMIN_USER.getMessage()));

        //관광지 정보 수정
        tourismApi.updateTourPlace(tourismUpdateReqDto, newFileGroup, badgeCode, adminUser.getDetailAreaCode().getCode(), adminUser.getDetailAreaCode().getAreaCode().getCode());
    }

    @Transactional
    public void deleteMultiTourism(List<String> tourismIds) {
        tourismIds.forEach(this::deleteTourism);
    }
}
