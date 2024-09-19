package kr.co.entity;

import jakarta.persistence.*;
import kr.co.dto.PushReqDto;
import kr.co.entity.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PushRead extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "푸시읽음키값")
    private String pushReadId;

    @ManyToOne
    @JoinColumn(name = "push_id")
    private Push push;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
