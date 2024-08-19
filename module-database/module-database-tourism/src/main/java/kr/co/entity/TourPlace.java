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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TourPlace {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "관광지키값")
    private String tourPlaceId;
    @Comment(value = "관광지명")
    private String tourPlaceName;
    @Comment(value = "주소")
    private String tourPlaceAddress;
    @Comment(value = "링크")
    private String tourPlaceLink;
    @Comment(value = "연락처")
    private String tourPlaceContact;
}
