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
public class CulturalHeritage {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "문화행사키값")
    private String culturalHeritageId;

    @Comment(value = "지정번호")
    @Column(length = 50)
    private String designationNumber;
    @Comment(value = "명칭")
    private String name;
    @Comment(value = "수량면적")
    private String quantityArea;

    @OneToOne
    @JoinColumn(name = "place_info_id")
    private PlaceInfo placeInfo;
}
