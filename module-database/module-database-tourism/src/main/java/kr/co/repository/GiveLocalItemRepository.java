package kr.co.repository;

import kr.co.entity.GiveLocalItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GiveLocalItemRepository extends JpaRepository<GiveLocalItem,String> {

    Page<GiveLocalItem> findAllByRegUserEmailAndDelYnFalse(PageRequest regDatetime, String userEmail);
}
