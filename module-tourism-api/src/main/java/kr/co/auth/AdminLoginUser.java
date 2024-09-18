package kr.co.auth;


import kr.co.entity.AdminUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class AdminLoginUser implements Serializable {

    private Collection<? extends GrantedAuthority> authorities;
    private AdminUser adminUser;


    public AdminLoginUser(AdminUser adminUser){
       this.adminUser = adminUser;
       this.authorities = new ArrayList<>(){{
           add(new SimpleGrantedAuthority("admin"));
       }};
    }
}
