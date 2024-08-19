package kr.co.service.admin;


import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.*;
import kr.co.entity.File;
import kr.co.entity.FileGroup;
import kr.co.entity.Goods;
import kr.co.entity.TourPlace;
import kr.co.repository.FileGroupRepository;
import kr.co.repository.FileRepository;
import kr.co.repository.GoodsRepository;
import kr.co.repository.TourPlaceRepository;
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
import java.util.Optional;
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

    @Transactional(readOnly = true)
    public Page<TourPlaceResDto> getTourPlaceList(int page, int size) {
        Page<TourPlace> tourPlaces = tourPlaceRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<TourPlaceResDto> tourPlaceResDtos = tourPlaces.stream()
                .map(tourPlaceDto -> TourPlaceResDto.builder()
                        .tourPlaceId(tourPlaceDto.getTourPlaceId())
                        .tourPlaceName(tourPlaceDto.getTourPlaceName())
                        .tourPlaceAddress(tourPlaceDto.getTourPlaceAddress())
                        .tourPlaceLink(tourPlaceDto.getTourPlaceLink())
                        .tourPlaceContact(tourPlaceDto.getTourPlaceContact())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(tourPlaceResDtos, tourPlaces.getPageable(), tourPlaces.getTotalElements());
    }

    @Transactional(readOnly = true)
    public TourPlaceDetailResDto getTourPlaceDetail(String tourPlaceId) {
        TourPlace tourPlace = tourPlaceRepository.findById(tourPlaceId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));
        return new TourPlaceDetailResDto(tourPlace);
    }

    @Transactional
    public void deleteTourPlace(String tourPlaceId) {
        TourPlace tourPlace = tourPlaceRepository.findById(tourPlaceId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        tourPlace.deleteTourPlace();
    }

    @Transactional
    public void uploadTourPlace(TourPlaceUploadReqDto tourPlaceUploadReqDto) {
        List<MultipartFile> images = tourPlaceUploadReqDto.getTourPlaceImages();
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }

        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(tourPlaceUploadReqDto.getTourPlaceImages(), uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));

        //관광지 정보 저장
        tourPlaceRepository.save(new TourPlace(tourPlaceUploadReqDto,fileGroup));
    }

    @Transactional
    public void updateTourPlace(String tourPlaceId, TourPlaceUploadReqDto tourPlaceUploadReqDto) {
        TourPlace tourPlace = tourPlaceRepository.findById(tourPlaceId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_TOUR_PLACE.getCode(), CommonErrorCode.NOT_FOUND_TOUR_PLACE.getMessage()));

        //기존 파일 삭제
        FileGroup fileGroup = fileGroupRepository.findById(tourPlace.getFileGroup().getFileGroupId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_FILE_GROUP.getCode(), CommonErrorCode.NOT_EXIST_FILE_GROUP.getMessage()));
        fileGroup.deleteFileGroup(true);
        //파일 업로드, 파일 그룹 저장
        FileGroup newFileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(tourPlaceUploadReqDto.getTourPlaceImages(), uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, newFileGroup))
                .collect(Collectors.toList()));

        //굿즈 상품 수정
        tourPlace.updateTourPlace(tourPlaceUploadReqDto,newFileGroup);
    }
}
