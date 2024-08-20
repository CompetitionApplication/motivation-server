package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanConverter;
import kr.co.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LocalItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "특산품키값")
    private String localItemId;

    @Comment(value = "특산품명")
    private String localItemName;

    @Comment(value = "특산품금액")
    private String localItemPrice;

    @Comment(value = "스탬프갯수")
    private int localItemStampCount;

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;

    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;

   /* public localItem(GoodsUploadReqDto goodsUploadReqDto, FileGroup fileGroup) {
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
    }*/

    public void deleteGoods() {
        this.delYn = true;
    }
}
