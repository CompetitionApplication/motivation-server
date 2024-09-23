package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanConverter;
import kr.co.dto.LocalItemUpdateReqDto;
import kr.co.dto.LocalItemUploadDataDto;
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

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;

    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;

    @ManyToOne
    @JoinColumn(name = "area_code_id")
    private AreaCode areaCode;

    @ManyToOne
    @JoinColumn(name = "detail_area_code_id")
    private DetailAreaCode detailAreaCode;

    public LocalItem(LocalItemUploadDataDto localItemUploadDataDto, AreaCode areaCode, DetailAreaCode detailAreaCode, FileGroup fileGroup) {
        this.localItemName = localItemUploadDataDto.getLocalItemName();
        this.localItemPrice = localItemUploadDataDto.getLocalItemPrice();
        this.areaCode = areaCode;
        this.detailAreaCode = detailAreaCode;
        this.fileGroup = fileGroup;
    }


    public void deleteLocalItem() {
        this.delYn = true;
    }

    public void updateLocalItem(LocalItemUpdateReqDto localItemUpdateReqDto, AreaCode areaCode, DetailAreaCode detailAreaCode, FileGroup fileGroup) {
        this.localItemName = localItemUpdateReqDto.getLocalItemName();
        this.localItemPrice = localItemUpdateReqDto.getLocalItemPrice();
        this.areaCode = areaCode;
        this.detailAreaCode = detailAreaCode;
        this.fileGroup = fileGroup;
    }
}
