package kr.co.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

@Setter
@Getter
public class TourismAdminUser extends User {

    public AdminLoginUser adminLoginUser;
    public TourismAdminUser(AdminLoginUser adminLoginUser) {
        super(adminLoginUser.getAdminUser().getAdminUserEmail()
                , ""
                , true
                , false
                , false
                , false
                , adminLoginUser.getAuthorities());
        this.adminLoginUser = adminLoginUser;
    }

}
