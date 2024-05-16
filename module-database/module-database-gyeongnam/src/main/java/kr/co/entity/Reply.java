package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanToYNConverter;
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
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "응답키값")
    private String replyId;
    @Column(name = "reply_content", length = 2000)
    private String replyContent;

    @Column(name = "reply_read_yn",columnDefinition = "VARCHAR(1) default 'N'")
    @Convert(converter = BooleanToYNConverter.class)
    @Comment(value = "답변 유무")
    private boolean replyReadYn;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
