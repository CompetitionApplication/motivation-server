package kr.co.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@Setter
@Getter
public class GyeongnamUser extends User {

    public LoginUser loginUser;
    public GyeongnamUser(LoginUser loginUser) {
        super(loginUser.getUser().getUserEmail()
                , ""
                , true
                , false
                , false
                , false
                , loginUser.getAuthorities());
        this.loginUser = loginUser;
    }

}
