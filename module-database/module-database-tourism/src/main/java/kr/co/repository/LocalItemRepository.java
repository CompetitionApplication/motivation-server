package kr.co.repository;

import kr.co.entity.LocalItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalItemRepository extends JpaRepository<LocalItem,String> {
    Page<LocalItem> findAllByDelYnFalse(PageRequest regDatetime);
}
