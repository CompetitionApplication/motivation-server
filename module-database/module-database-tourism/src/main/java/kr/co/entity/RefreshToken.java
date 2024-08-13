package kr.co.entity;

import jakarta.persistence.*;
import kr.co.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Comment(value = "리프레시토큰키값")
    private String refreshTokenId;
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RefreshToken(String refreshToken, User user) {
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
