package kr.co.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@Setter
@Getter
public class AgricultureUser extends User {

    public LoginUser loginUser;
    public AgricultureUser(LoginUser loginUser) {
        super(loginUser.getUser().getUser_email()
                , ""
                , true
                , false
                , false
                , false
                , loginUser.getAuthorities());
        this.loginUser = loginUser;
    }

}
