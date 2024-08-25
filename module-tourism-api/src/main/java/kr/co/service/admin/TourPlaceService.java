package kr.co.service.admin;


import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.*;
import kr.co.entity.File;
import kr.co.entity.FileGroup;
import kr.co.entity.Tourism;
import kr.co.entity.TourismApi;
import kr.co.repository.FileGroupRepository;
import kr.co.repository.FileRepository;
import kr.co.repository.TourPlaceRepository;
import kr.co.repository.TourismApiRepository;
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
public class TourPlaceService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final TourPlaceRepository tourPlaceRepository;
    private final FileRepository fileRepository;
    private final FileGroupRepository fileGroupRepository;
    private final TourismApiRepository tourismApiRepository;

    @Transactional(readOnly = true)
    public Page<TourPlaceResDto> getTourPlaceList(int page, int size) {
        // 관광지 외부 API DATA (한국어 버전만 추출)
        List<TourismApi> tourismApis = tourismApiRepository.findAllByDelYnFalseAndCountry("KOR");

        // 기존 DB의 관광지 데이터 가져오기
        Page<Tourism> tourPlaces = tourPlaceRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));

        // DB 데이터를 DTO로 변환
        List<TourPlaceResDto> tourPlaceResDtos = tourPlaces.stream()
                .map(tourism -> TourPlaceResDto.builder()
                        .tourPlaceId(tourism.getTourPlaceId())
                        .tourPlaceName(tourism.getTourPlaceName())
                        .tourPlaceAddress(tourism.getTourPlaceAddress())
                        .tourPlaceLink(tourism.getTourPlaceLink())
                        .tourPlaceContact(tourism.getTourPlaceContact())
                        .build())
                .collect(Collectors.toList());

        // 외부 API 데이터를 DTO로 변환하여 추가
        List<TourPlaceResDto> tourismApiDtos = tourismApis.stream()
                .map(api -> TourPlaceResDto.builder()
                        .tourPlaceId(api.getContentid())
                        .tourPlaceName(api.getTitle())
                        .tourPlaceAddress(api.getAddr1() + " " + api.getAddr2())
                        .tourPlaceContact(api.getTel())
                        .build())
                .collect(Collectors.toList());

        // 두 리스트를 합침
        tourPlaceResDtos.addAll(tourismApiDtos);

        // 페이징 처리를 위해 리스트를 다시 나눔
        int start = Math.min((int) PageRequest.of(page, size).getOffset(), tourPlaceResDtos.size());
        int end = Math.min((start + size), tourPlaceResDtos.size());

        return new PageImpl<>(tourPlaceResDtos.subList(start, end), PageRequest.of(page, size), tourPlaceResDtos.size());
    }

    @Transactional(readOnly = true)
    public TourPlaceDetailResDto getTourPlaceDetail(String tourPlaceId) {
        Tourism tourism = tourPlaceRepository.findById(tourPlaceId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));
        return new TourPlaceDetailResDto(tourism);
    }

    @Transactional
    public void deleteTourPlace(String tourPlaceId) {
        Tourism tourism = tourPlaceRepository.findById(tourPlaceId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        tourism.deleteTourPlace();
    }

    @Transactional
    public void uploadTourPlace(TourPlaceUploadReqDto tourPlaceUploadReqDto, List<MultipartFile> tourPlaceImages) {
        List<MultipartFile> images = tourPlaceImages;
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }

        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(tourPlaceImages, uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));

        //관광지 정보 저장
        tourPlaceRepository.save(new Tourism(tourPlaceUploadReqDto, fileGroup));
    }

    @Transactional
    public void updateTourPlace(String tourPlaceId, TourPlaceUploadReqDto tourPlaceUploadReqDto, List<MultipartFile> tourPlaceImages) {
        Tourism tourism = tourPlaceRepository.findById(tourPlaceId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        //기존 파일 삭제
        FileGroup fileGroup = fileGroupRepository.findById(tourism.getFileGroup().getFileGroupId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_FILE_GROUP.getCode(), CommonErrorCode.NOT_EXIST_FILE_GROUP.getMessage()));
        fileGroup.deleteFileGroup(true);
        //파일 업로드, 파일 그룹 저장
        FileGroup newFileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(tourPlaceImages, uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, newFileGroup))
                .collect(Collectors.toList()));

        //굿즈 상품 수정
        tourism.updateTourPlace(tourPlaceUploadReqDto, newFileGroup);
    }
}
