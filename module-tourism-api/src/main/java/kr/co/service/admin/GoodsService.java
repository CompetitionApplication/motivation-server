package kr.co.service.admin;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.FileSaveDto;
import kr.co.dto.GoodsDetailResDto;
import kr.co.dto.GoodsResDto;
import kr.co.dto.GoodsUploadReqDto;
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
public class GoodsService {

    private final AreaCodeRepository areaCodeRepository;
    private final DetailAreaCodeRepository detailAreaCodeRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    private final GoodsRepository goodsRepository;
    private final FileRepository fileRepository;
    private final FileGroupRepository fileGroupRepository;

    @Transactional(readOnly = true)
    public Page<GoodsResDto> getGoodsList(int page, int size) {
        Page<Goods> goodsList = goodsRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<GoodsResDto> goodsResDtos = goodsList.stream()
                .map(goods -> GoodsResDto.builder()
                        .goodsId(goods.getGoodsId())
                        .goodsName(goods.getGoodsName())
                        .goodsPrice(goods.getGoodsPrice() + "원")
                        .goodsColor(goods.getGoodsColor())
                        .goodsSize(goods.getGoodsSize())
                        .goodsFrom(goods.getGoodsFrom())
                        .goodsReleaseDate(goods.getGoodsReleaseDate())
                        .goodsDeliveryDate(goods.getGoodsDeliveryDate())
                        .badgeOpenCount(goods.getBadgeOpenCount())
                        .areaCode(goods.getAreaCode().getName())
                        .detailAreaCode(goods.getDetailAreaCode().getName())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(goodsResDtos, goodsList.getPageable(), goodsList.getTotalElements());
    }

    @Transactional
    public void uploadGoods(GoodsUploadReqDto goodsUploadReqDto, List<MultipartFile> goodsImages, String areaCodeId, String detailAreaCodeId) {
        List<MultipartFile> images = goodsImages;
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }

        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(images, uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));

        AreaCode areaCode = areaCodeRepository.findById(areaCodeId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_AREA_CODE.getCode(), CommonErrorCode.NOT_EXIST_AREA_CODE.getMessage()));

        DetailAreaCode detailAreaCode = detailAreaCodeRepository.findById(detailAreaCodeId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_DETAIL_AREA_CODE.getCode(), CommonErrorCode.NOT_EXIST_DETAIL_AREA_CODE.getMessage()));

        //상품 정보 저장
        goodsRepository.save(new Goods(goodsUploadReqDto, fileGroup,areaCode,detailAreaCode));
    }

    @Transactional
    public void deleteGoods(String goodsId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GOODS.getCode(), CommonErrorCode.NOT_EXIST_GOODS.getMessage()));
        goods.deleteGoods();
    }

    @Transactional
    public void updateGoods(String goodsId, GoodsUploadReqDto goodsUploadReqDto, List<MultipartFile> goodsImages, String areaCodeId, String detailAreaCodeId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GOODS.getCode(), CommonErrorCode.NOT_EXIST_GOODS.getMessage()));

        //기존 파일 삭제
        FileGroup fileGroup = fileGroupRepository.findById(goods.getFileGroup().getFileGroupId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_FILE_GROUP.getCode(), CommonErrorCode.NOT_EXIST_FILE_GROUP.getMessage()));
        fileGroup.deleteFileGroup(true);
        //파일 업로드, 파일 그룹 저장
        FileGroup newFileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(goodsImages, uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, newFileGroup))
                .collect(Collectors.toList()));

        AreaCode areaCode = areaCodeRepository.findById(areaCodeId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_AREA_CODE.getCode(), CommonErrorCode.NOT_EXIST_AREA_CODE.getMessage()));

        DetailAreaCode detailAreaCode = detailAreaCodeRepository.findById(detailAreaCodeId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_DETAIL_AREA_CODE.getCode(), CommonErrorCode.NOT_EXIST_DETAIL_AREA_CODE.getMessage()));

        //굿즈 상품 수정
        goods.updateGoods(goodsUploadReqDto, newFileGroup,areaCode,detailAreaCode);

    }

    @Transactional(readOnly = true)
    public GoodsDetailResDto getGoodsDetail(String goodsId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GOODS.getCode(), CommonErrorCode.NOT_EXIST_GOODS.getMessage()));

        //test
        List<String> goodsImages = goods.getFileGroup().getFiles().stream()
                .map(file -> file.getFilePath())
                .collect(Collectors.toList());

        return new GoodsDetailResDto(goods,goodsImages);
    }
}
