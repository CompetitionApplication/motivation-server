package kr.co.dto.app.common;

import kr.co.entity.AdminUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class ServiceAdminUser implements UserDetails {

    /** 회원관리번호 */
    private String userId;

    /** eamil */
    private String userEmail;

    //security 기본
    private String password;
    private String userName;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public ServiceAdminUser(AdminUser adminUser){
        this.userId = adminUser.getAdminUserEmail();
        this.userEmail = adminUser.getAdminUserEmail();
        this.password = adminUser.getAdminUserPassword();
        this.userName = adminUser.getAdminUserEmail();
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }
}
