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
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "알림키값")
    private String alarmId;

    @Column(name = "alarm_kind", length = 50)
    private String alarmKind;

    @Column(name = "alarm_title", length = 500)
    private String alarmTitle;

    @Column(name = "alarm_content", length = 2000)
    private String alarmContent;

    @Column(name = "alarm_read_yn",columnDefinition = "VARCHAR(1) default 'N'")
    @Convert(converter = BooleanToYNConverter.class)
    @Comment(value = "알림 읽음 유무")
    private boolean alarmReadYn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
