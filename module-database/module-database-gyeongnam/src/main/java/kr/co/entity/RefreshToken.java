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
public class RefreshToken extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    @Comment(value = "리프레시토큰키값")
    private String refreshtokenId;

    @Column(name = "refreshtoken")
    private String refreshtoken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RefreshToken(String refreshToken, User user) {
        this.refreshtoken = refreshToken;
        this.user = user;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshtoken = refreshToken;
    }
}
