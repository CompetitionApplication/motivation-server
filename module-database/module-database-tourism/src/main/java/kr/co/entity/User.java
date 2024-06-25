package kr.co.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.co.common.AES256Util;
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
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "유저키값")
    private String userId;

    @Column(length = 100)
    private String userEmail;

    @Column(length = 100)
    private String userName;
    @Column(length = 1)
    private String userSex;

    @Column(length = 2)
    private String userAge;

    @Column(length = 10)
    private String userTourPeriod;

    public User(LoginReqDto dto) throws Exception {
        this.userEmail = AES256Util.encrypt(dto.getUserEmail());
        this.userName = dto.getUserName();
        this.userSex = dto.getUserSex();
        this.userAge = dto.getUserAge();
        this.userTourPeriod = dto.getUserTourPeriod();
    }
}
