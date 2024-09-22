package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class GoodsUpdateReqDto {
    @Schema(description = "굿즈명", example = "티셔츠")
    private String goodsName;
    @Schema(description = "굿즈금액", example = "10000")
    private String goodsPrice;
    @Schema(description = "뱃지 해금 개수", example = "1")
    private Integer badgeOpenCount;
    @Schema(description = "굿즈색상", example = "빨강")
    private String goodsColor;
    @Schema(description = "굿즈사이즈", example = "L")
    private String goodsSize;
    @Schema(description = "발송지", example = "서울")
    private String goodsFrom;
    @Schema(description = "출시일", example = "2021-01-01")
    private String goodsReleaseDate;
    @Schema(description = "예상 배송일", example = "2021-01-01")
    private String goodsDeliveryDate;
    private List<MultipartFile> goodsImages;
    private String areaCodeId;
    private String detailAreaCodeId;

    public GoodsUpdateReqDto(GoodsUpdateDto goodsUpdateDto) {
        this.goodsName = goodsUpdateDto.getGoodsName();
        this.goodsPrice = goodsUpdateDto.getGoodsPrice();
        this.badgeOpenCount = goodsUpdateDto.getBadgeOpenCount();
        this.goodsColor = goodsUpdateDto.getGoodsColor();
        this.goodsSize = goodsUpdateDto.getGoodsSize();
        this.goodsFrom = goodsUpdateDto.getGoodsFrom();
        this.goodsReleaseDate = goodsUpdateDto.getGoodsReleaseDate();
        this.goodsDeliveryDate = goodsUpdateDto.getGoodsDeliveryDate();
        this.goodsImages = goodsUpdateDto.getGoodsImages();
        this.areaCodeId = goodsUpdateDto.getAreaCodeId();
        this.detailAreaCodeId = goodsUpdateDto.getDetailAreaCodeId();

    }
}
