package kr.co.repository;

import kr.co.entity.RefreshToken;
import kr.co.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {
    Optional<RefreshToken> findByUser(User userInfo);
}
