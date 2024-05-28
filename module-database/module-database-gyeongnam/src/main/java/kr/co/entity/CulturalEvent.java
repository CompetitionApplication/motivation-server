package kr.co.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CulturalEvent {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "문화행사키값")
    private String culturalEventId;
    @Comment(value = "고유번호")
    @Column(length = 50)
    private String uniqueCode;
    @Comment(value = "제목")
    private String title;
    @Comment(value = "행사시작일")
    private LocalDate eventStartDate;
    @Comment(value = "행사종료일")
    private LocalDate eventEndDate;
    @Comment(value = "시간")
    @Column(length = 50)
    private String time;
    @Comment(value = "장소")
    private String venue;
    @Comment(value = "행사주최자")
    private String organizer;

    @OneToOne
    @JoinColumn(name = "place_info_id")
    private PlaceInfo placeInfo;
}
