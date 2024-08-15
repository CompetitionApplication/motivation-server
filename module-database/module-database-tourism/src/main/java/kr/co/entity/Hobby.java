package kr.co.entity;

import jakarta.persistence.*;
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
public class Hobby extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "취미키값")
    private String hobbyId;

    @Comment(value = "취미이름")
    private String hobbyName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Hobby(String hobbyName, User user) {
        this.hobbyName = hobbyName;
        this.user = user;
    }
}
