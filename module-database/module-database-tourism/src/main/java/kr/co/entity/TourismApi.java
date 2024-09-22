package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanConverter;
import kr.co.dto.TourismUpdateReqDto;
import kr.co.dto.TourismUploadReqDto;
import kr.co.entity.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TourismApi extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    private String tourismApiId;
    private String addr1;
    private String addr2;
    //지역코드
    private String areacode;
    //지역상세코드
    private String detailAreaCode;
    private String booktour;
    private String cat1;
    private String cat2;
    private String cat3;
    private String contentid;
    private String contenttypeid;
    private String createdtime;
    private String firstimage;
    private String firstimage2;
    private String cpyrhtDivCd;
    private String mapx;
    private String mapy;
    private String mlevel;
    private String modifiedtime;
    private String sigungucode;
    private String tel;
    private String title;
    private String zipcode;
    private String country;
    private String tourismLink;

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean customYn;
    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;
    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;

    @ManyToOne
    @JoinColumn(name = "badge_code")
    private BadgeCode badgeCode;

    public void delete() {
        this.delYn = true;
    }

    public TourismApi(TourismUploadReqDto tourismUploadReqDto, FileGroup fileGroup, BadgeCode badgeCode, String areacode, String detailAreaCode) {
        this.title = tourismUploadReqDto.getTourismName();
        this.addr1 = tourismUploadReqDto.getTourismAddress();
        this.addr2 = "";
        this.tel = tourismUploadReqDto.getTourismContact();
        this.tourismLink = tourismUploadReqDto.getTourismLink();
        this.areacode = areacode;
        this.detailAreaCode = detailAreaCode;
        this.mapx = tourismUploadReqDto.getTourismMapX();
        this.mapy = tourismUploadReqDto.getTourismMapY();
        this.fileGroup = fileGroup;
        this.badgeCode = badgeCode;
        this.country = "KOR";
        this.customYn = true;
    }

    public void updateTourPlace(TourismUpdateReqDto tourPlaceUpdateReqDto, FileGroup newFileGroup, BadgeCode badgeCode, String detailAreaCode, String areaCode) {
        this.title = tourPlaceUpdateReqDto.getTourismName();
        this.addr1 = tourPlaceUpdateReqDto.getTourismAddress();
        this.tel = tourPlaceUpdateReqDto.getTourismContact();
        this.tourismLink = tourPlaceUpdateReqDto.getTourismLink();
        this.areacode = areaCode;
        this.detailAreaCode = detailAreaCode;
        this.mapx = tourPlaceUpdateReqDto.getTourismMapX();
        this.mapy = tourPlaceUpdateReqDto.getTourismMapY();
        this.fileGroup = newFileGroup;
        this.badgeCode = badgeCode;
        this.customYn = true;
    }

    public void deleteTourism() {
        this.delYn = true;
    }
}
