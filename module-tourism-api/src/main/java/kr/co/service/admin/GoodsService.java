package kr.co.service.admin;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.FileSaveDto;
import kr.co.dto.GoodsDetailResDto;
import kr.co.dto.GoodsResDto;
import kr.co.dto.GoodsUploadReqDto;
import kr.co.entity.File;
import kr.co.entity.FileGroup;
import kr.co.entity.Goods;
import kr.co.repository.FileGroupRepository;
import kr.co.repository.FileRepository;
import kr.co.repository.GoodsRepository;
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

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final GoodsRepository goodsRepository;
    private final FileRepository fileRepository;
    private final FileGroupRepository fileGroupRepository;

    @Transactional(readOnly = true)
    public Page<GoodsResDto> getGoodsList(int page, int size) {
        Page<Goods> goods = goodsRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<GoodsResDto> goodsResDtos = goods.stream()
                .map(goodsResDto -> GoodsResDto.builder()
                        .goodsId(goodsResDto.getGoodsId())
                        .goodsName(goodsResDto.getGoodsName())
                        .goodsPrice(goodsResDto.getGoodsPrice() + "원")
                        .goodsColor(goodsResDto.getGoodsColor())
                        .goodsSize(goodsResDto.getGoodsSize())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(goodsResDtos, goods.getPageable(), goods.getTotalElements());
    }

    @Transactional
    public void uploadGoods(GoodsUploadReqDto goodsUploadReqDto) {
        List<MultipartFile> images = goodsUploadReqDto.getGoodsImages();
        if (images.isEmpty()) {
            throw new CommonException(CommonErrorCode.NOT_EXIST_FILE.getCode(), CommonErrorCode.NOT_EXIST_FILE.getMessage());
        }

        //파일 업로드, 파일 그룹 저장
        FileGroup fileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(goodsUploadReqDto.getGoodsImages(), uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, fileGroup))
                .collect(Collectors.toList()));

        //상품 정보 저장
        goodsRepository.save(new Goods(goodsUploadReqDto,fileGroup));
    }

    @Transactional
    public void deleteGoods(String goodsId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GOODS.getCode(), CommonErrorCode.NOT_EXIST_GOODS.getMessage()));
        goods.deleteGoods();
    }

    @Transactional
    public void updateGoods(String goodsId, GoodsUploadReqDto goodsUploadReqDto) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GOODS.getCode(), CommonErrorCode.NOT_EXIST_GOODS.getMessage()));

        //기존 파일 삭제
        FileGroup fileGroup = fileGroupRepository.findById(goods.getFileGroup().getFileGroupId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_FILE_GROUP.getCode(), CommonErrorCode.NOT_EXIST_FILE_GROUP.getMessage()));
        fileGroup.deleteFileGroup(true);
        //파일 업로드, 파일 그룹 저장
        FileGroup newFileGroup = fileGroupRepository.save(new FileGroup(false));
        List<FileSaveDto> fileSaveDtos = FileUtil.uploadFile(goodsUploadReqDto.getGoodsImages(), uploadDir);
        fileRepository.saveAll(fileSaveDtos.stream()
                .map(fileSaveDto -> new File(fileSaveDto, newFileGroup))
                .collect(Collectors.toList()));

        //굿즈 상품 수정
        goods.updateGoods(goodsUploadReqDto,newFileGroup);

    }

    @Transactional(readOnly = true)
    public GoodsDetailResDto getGoodsDetail(String goodsId) {
        Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_EXIST_GOODS.getCode(), CommonErrorCode.NOT_EXIST_GOODS.getMessage()));

        return new GoodsDetailResDto(goods);
    }
}
