package kr.co.entity;

import jakarta.persistence.*;
import kr.co.common.AES256Cipher;
import kr.co.dto.LoginReqDto;
import kr.co.dto.SignUpReqDto;
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

    @Enumerated(EnumType.STRING)
    @Comment(value = "소셜로그인종류")
    private SocialType socialType;

    @Comment(value = "앱디바이스토큰")
    private String appDeviceToken;

    @Column(length = 100)
    @Comment(value = "이메일")
    private String userEmail;

    @Column(length = 100)
    @Comment(value = "이름")
    private String userName;

    @Column(length = 1)
    @Comment(value = "성별")
    private String userSex;

    @Column(length = 2)
    @Comment(value = "나이")
    private String userAge;


    public User(SignUpReqDto dto) throws Exception {
        this.socialType = SocialType.valueOf(dto.getSocialType());
        this.userEmail = AES256Cipher.encrypt(dto.getUserEmail());
        this.appDeviceToken = dto.getAppDeviceToken();
        this.userName = dto.getUserName();
        this.userSex = dto.getUserSex();
        this.userAge = dto.getUserAge();
    }
}
