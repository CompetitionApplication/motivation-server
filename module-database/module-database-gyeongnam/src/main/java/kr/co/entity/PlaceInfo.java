package kr.co.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceInfo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "장소정보키값")
    private String placeInfoId;

    @Comment(value = "장소제목")
    private String placeTitle;
    @Comment(value = "시군구명")
    private String city_country;
    @Comment(value = "장소설명")
    private String description;
    @Comment(value = "위도")
    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;
    @Comment(value = "경도")
    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;
    @Comment(value = "전화")
    @Column(length = 50)
    private String phone;
    @Comment(value = "홈페이지")
    private String website;
    @Comment(value = "주소")
    private String address;
    @Comment(value = "교통편")
    private String transport;



}
