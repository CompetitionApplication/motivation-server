package kr.co.repository;

import kr.co.entity.GiveLocalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface GiveLocalItemRepository extends JpaRepository<GiveLocalItem,String> {
    List<GiveLocalItem> findAllByRegUserEmail(String regUserEmail);
}
