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
    @GenericGenerator(name="uuid2", strategy = "uuid2")
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

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;

    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;

    public Goods(GoodsUploadReqDto goodsUploadReqDto, FileGroup fileGroup) {
        this.goodsName = goodsUploadReqDto.getGoodsName();
        this.goodsPrice = goodsUploadReqDto.getGoodsPrice();
        this.goodsColor = goodsUploadReqDto.getGoodsColor();
        this.goodsSize = goodsUploadReqDto.getGoodsSize();
        this.fileGroup = fileGroup;
    }

    public void updateGoods(GoodsUploadReqDto goodsUploadReqDto, FileGroup fileGroup) {
        this.goodsName = goodsUploadReqDto.getGoodsName();
        this.goodsPrice = goodsUploadReqDto.getGoodsPrice();
        this.goodsColor = goodsUploadReqDto.getGoodsColor();
        this.goodsSize = goodsUploadReqDto.getGoodsSize();
        this.fileGroup = fileGroup;
    }

    public void deleteGoods() {
        this.delYn = true;
    }
}
