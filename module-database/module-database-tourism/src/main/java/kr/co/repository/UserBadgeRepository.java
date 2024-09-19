package kr.co.repository;


import kr.co.entity.User;
import kr.co.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRepository extends JpaRepository<UserBadge, String> {
    long countByUser(User userInfo);
}
