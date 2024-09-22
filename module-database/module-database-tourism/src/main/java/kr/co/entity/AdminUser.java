package kr.co.entity;

import jakarta.persistence.*;
import kr.co.dto.FileSaveDto;
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
public class AdminUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "어드민키값")
    private String adminUserId;
    private String adminUserEmail;
    private String adminUserPassword;

    @OneToOne
    @JoinColumn(name = "detailAreaCodeId")
    private DetailAreaCode detailAreaCode;
}
