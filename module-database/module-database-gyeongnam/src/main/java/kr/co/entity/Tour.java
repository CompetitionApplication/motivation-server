package kr.co.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Tour extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "여행지키값")
    private String tourId;
    @Column(name = "tour_name", length = 100)
    private String tourName;

    @Column(name = "tour_zip", length = 100)
    private String tourZip;

    @Column(name = "tour_zip_detail", length = 100)
    private String tourZipDetail;

    @Column(name = "tour_zip_code", length = 5)
    private String tourZipCode;

    @Column(name = "tour_image", length = 100)
    private String tourImage;

    @Column(name = "tour_intro", length = 2000)
    private String tourIntro;

    @Column(name = "latitude", length = 10)
    private String latitude;

    @Column(name = "longitude", length = 10)
    private String longitude;

}
