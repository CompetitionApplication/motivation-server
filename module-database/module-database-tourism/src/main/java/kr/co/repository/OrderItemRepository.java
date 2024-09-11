package kr.co.repository;

import kr.co.entity.OrderItem;
import kr.co.entity.RefreshToken;
import kr.co.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
    Page<OrderItem> findAllByDelYnFalse(PageRequest regDatetime);
}
