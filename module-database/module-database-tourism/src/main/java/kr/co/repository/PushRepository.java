package kr.co.repository;

import kr.co.entity.Push;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PushRepository extends JpaRepository<Push,String> {
    List<Push> findTop7ByOrderByRegDatetimeDesc();
}
