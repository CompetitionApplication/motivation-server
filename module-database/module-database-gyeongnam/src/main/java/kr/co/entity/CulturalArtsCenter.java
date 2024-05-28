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
public class CulturalArtsCenter {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "문화예술회관키값")
    private String culturalArtsCenterId;

    @Column(length = 50)
    @Comment(value = "고유번호")
    private String uniqueCode;
    @Comment(value = "기관명")
    private String institutionName;
    @Comment(value = "관리기관명")
    private String managingAgency;
    @OneToOne
    @JoinColumn(name = "place_info_id")
    private PlaceInfo placeInfo;
}
