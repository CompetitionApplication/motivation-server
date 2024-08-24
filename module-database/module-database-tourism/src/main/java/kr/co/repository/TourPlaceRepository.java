package kr.co.repository;

import kr.co.entity.Tourism;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPlaceRepository extends JpaRepository<Tourism,String> {
    Page<Tourism> findAllByDelYnFalse(PageRequest regDatetime);
}
