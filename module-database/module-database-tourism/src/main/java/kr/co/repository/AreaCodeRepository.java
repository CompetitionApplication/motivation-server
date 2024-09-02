package kr.co.repository;

import kr.co.entity.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaCodeRepository extends JpaRepository<AreaCode,String> {
    List<AreaCode> findAllByCountry(String country);

    List<AreaCode> findAllByDelYnFalseAndCountry(String country);
}
