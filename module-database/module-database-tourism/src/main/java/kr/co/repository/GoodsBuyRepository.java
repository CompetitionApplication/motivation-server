package kr.co.repository;

import kr.co.entity.GoodsBuy;
import kr.co.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GoodsBuyRepository extends JpaRepository<GoodsBuy,String> {
    List<GoodsBuy> findByUser(User userInfo);
}
