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
public class Push extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "푸시키값")
    private String pushId;
    private String pushTitle;
    private String pushContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Push(PushReqDto pushReqDto){
        this.pushTitle = pushReqDto.getPushTitle();
        this.pushContent = pushReqDto.getPushContent();
    }
}
