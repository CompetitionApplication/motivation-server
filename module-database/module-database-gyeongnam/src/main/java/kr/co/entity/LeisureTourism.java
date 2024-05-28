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
public class LeisureTourism {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "레저관광")
    private String leisureTourismId;
    @Comment(value = "레저구분")
    @Column(length = 50)
    private String leisureType;
    @Comment(value = "대표연락처")
    @Column(length = 50)
    private String mainContact;
    @Comment(value = "담당기관")
    private String managingAgency;
    @OneToOne
    @JoinColumn(name = "place_info_id")
    private PlaceInfo placeInfo;
    @OneToOne
    @JoinColumn(name = "file_group_id")
    private FileGroup fileGroup;
}
