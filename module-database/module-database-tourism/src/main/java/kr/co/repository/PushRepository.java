package kr.co.repository;

import kr.co.entity.Push;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushRepository extends JpaRepository<Push,String> {
}
