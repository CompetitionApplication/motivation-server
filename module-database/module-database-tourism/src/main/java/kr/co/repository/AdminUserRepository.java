package kr.co.repository;

import kr.co.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser,String> {
    Optional<AdminUser> findByAdminUserEmailAndAdminUserPassword(String adminUserEmail, String password);

    Optional<AdminUser> findByAdminUserEmail(String userId);
}
