package kr.co.entity;

import jakarta.persistence.*;
import kr.co.common.AES256Cipher;
import kr.co.config.BooleanConverter;
import kr.co.dto.GoodsUploadReqDto;
import kr.co.dto.LoginReqDto;
import kr.co.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "굿즈키값")
    private String goodsId;

    @Comment(value = "굿즈명")
    private String goodsName;

    @Comment(value = "굿즈금액")
    private String goodsPrice;

    @Comment(value = "굿즈색상")
    private String goodsColor;

    @Comment(value = "굿즈사이즈")
    private String goodsSize;

    @Comment(value = "발송지")
    private String goodsFrom;

    @Comment(value = "상품 출시일")
    private String goodsReleaseDate;

    @Comment(value = "예상 배송일")
    private String goodsDeliveryDate;

    @Comment(value = "뱃지 해금 개수")
    private Integer badgeOpenCount;

    @Comment(value = "굿즈 해금 유무")
    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean openYn;

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;

    @ManyToOne
    @JoinColumn(name = "area_code_id")
    private AreaCode areaCode;

    @ManyToOne
    @JoinColumn(name = "detail_area_code_id")
    private DetailAreaCode detailAreaCode;

    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;

    public Goods(GoodsUploadReqDto goodsUploadReqDto, FileGroup fileGroup, AreaCode areaCode, DetailAreaCode detailAreaCode) {
        this.goodsName = goodsUploadReqDto.getGoodsName();
        this.goodsPrice = goodsUploadReqDto.getGoodsPrice();
        this.goodsColor = goodsUploadReqDto.getGoodsColor();
        this.goodsSize = goodsUploadReqDto.getGoodsSize();
        this.fileGroup = fileGroup;
        this.areaCode = areaCode;
        this.detailAreaCode = detailAreaCode;
    }

    public void updateGoods(GoodsUploadReqDto goodsUploadReqDto, FileGroup fileGroup, AreaCode areaCode, DetailAreaCode detailAreaCode) {
        this.goodsName = goodsUploadReqDto.getGoodsName();
        this.goodsPrice = goodsUploadReqDto.getGoodsPrice();
        this.goodsColor = goodsUploadReqDto.getGoodsColor();
        this.goodsSize = goodsUploadReqDto.getGoodsSize();
        this.goodsFrom = goodsUploadReqDto.getGoodsFrom();
        this.goodsReleaseDate = goodsUploadReqDto.getGoodsReleaseDate();
        this.goodsDeliveryDate = goodsUploadReqDto.getGoodsDeliveryDate();
        this.fileGroup = fileGroup;
        this.areaCode = areaCode;
        this.detailAreaCode = detailAreaCode;
    }

    public void deleteGoods() {
        this.delYn = true;
    }
}
