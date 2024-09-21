package kr.co.repository;

import kr.co.entity.TourismFavorite;
import kr.co.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourismFavoriteRepository extends JpaRepository<TourismFavorite, String> {
    Optional<TourismFavorite> findAllByUser(User userInfo);
}
