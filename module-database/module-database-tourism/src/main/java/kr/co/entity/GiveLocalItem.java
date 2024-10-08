package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanConverter;
import kr.co.dto.GiveLocalItemReqDto;;
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
public class GiveLocalItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "특산품제공키값")
    private String giveLocalItemId;

    private String giveLocalItemName;

    private String giveLocalItemPrice;

    private String specialBadgeCodeName;

    @ManyToOne
    @JoinColumn(name = "badge_code")
    private BadgeCode badgeCode;

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;

    private String regUserEmail;


    public GiveLocalItem(GiveLocalItemReqDto giveLocalItemReqDto, BadgeCode badgeCode, String regUserEmail) {
        this.giveLocalItemName = giveLocalItemReqDto.getGiveLocalItemName();
        this.giveLocalItemPrice = giveLocalItemReqDto.getGiveLocalItemPrice();
        this.specialBadgeCodeName = giveLocalItemReqDto.getSpecialBadgeCodeName();
        this.regUserEmail = regUserEmail;
        this.badgeCode = badgeCode;
    }

    public void updateGiveLocalItem(GiveLocalItemReqDto giveLocalItemReqDto, BadgeCode badgeCode) {
        this.giveLocalItemName = giveLocalItemReqDto.getGiveLocalItemName();
        this.giveLocalItemPrice = giveLocalItemReqDto.getGiveLocalItemPrice();
        this.specialBadgeCodeName = giveLocalItemReqDto.getSpecialBadgeCodeName();
        this.badgeCode = badgeCode;
    }

    public void deleteGiveLocalItem() {
        this.delYn = true;
    }
}

