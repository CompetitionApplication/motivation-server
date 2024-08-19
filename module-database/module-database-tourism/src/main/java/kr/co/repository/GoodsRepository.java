package kr.co.repository;

import kr.co.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods,String> {
    Page<Goods> findAllByDelYnFalse(PageRequest regDatetime);
}
