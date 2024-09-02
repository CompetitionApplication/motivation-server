package kr.co.repository;

import kr.co.entity.AreaCode;
import kr.co.entity.DetailAreaCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DetailAreaCodeRepository extends JpaRepository<DetailAreaCode,String> {
    List<DetailAreaCode> findAllByDelYnFalseAndAreaCodeAndCountry(AreaCode areaCode, String country);
}
