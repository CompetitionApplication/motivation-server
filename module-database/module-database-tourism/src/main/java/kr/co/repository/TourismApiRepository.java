package kr.co.repository;

import kr.co.entity.TourismApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourismApiRepository extends JpaRepository<TourismApi,String> {

    List<TourismApi> findAllByDelYnFalseAndCountry(String country);

    List<TourismApi> findAllByDelYnFalseAndCountryAndAreacode(String kor, String code);
}
