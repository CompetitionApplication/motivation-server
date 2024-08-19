package kr.co.repository;

import kr.co.entity.TourPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourPlaceRepository extends JpaRepository<TourPlace,String> {
    Page<TourPlace> findAllByDelYnFalse(PageRequest regDatetime);
}