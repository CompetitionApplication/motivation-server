package kr.co.repository;

import kr.co.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUserEmailAndDelYnFalse(String userId);

    boolean existsByUserEmailAndDelYnFalse(String userEmail);

}
