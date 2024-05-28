package kr.co.entity;


import jakarta.persistence.*;
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
public class WalkingThemeTrail {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "걷고싶은테마키값")
    private String walkingThemeTrailId;
    @Comment(value = "소요시간")
    @Column(length = 50)
    private String duration;
    @Comment(value = "길이")
    @Column(length = 50)
    private String length;
    @Comment(value = "코스안내")
    private String courseGuide;
    @Comment(value = "코스소개")
    private String courseIntro;
    @Comment(value = "영상url")
    private String videoUrl;
    @OneToOne
    @JoinColumn(name = "place_info_id")
    private PlaceInfo placeInfo;
    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;
}
