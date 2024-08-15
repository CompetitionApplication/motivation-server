package kr.co.entity;

import jakarta.persistence.*;
import kr.co.dto.LoginReqDto;
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
public class TripStyle extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "여행스타일키값")
    private String tripStyleId;

    @Comment(value = "여행스타일이름")
    private String tripStyleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TripStyle(String tripStyleName, User user) {
        this.tripStyleName = tripStyleName;
        this.user = user;
    }
}
