package kr.co.repository;

import kr.co.entity.LocalSpecialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalSpecialtyRepository extends JpaRepository<LocalSpecialty,String> {
    Page<LocalSpecialty> findAllByDelYnFalse(PageRequest regDatetime);
}
