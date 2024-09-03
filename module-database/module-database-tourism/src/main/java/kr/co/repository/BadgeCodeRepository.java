package kr.co.repository;

import kr.co.entity.BadgeCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeCodeRepository extends JpaRepository<BadgeCode, String> {
}
