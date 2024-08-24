package kr.co.entity;

import jakarta.persistence.*;
import kr.co.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "varchar(38)")
    private String orderId;

    @Comment(value = "주문금액")
    private int orderPrice;

    @Enumerated(EnumType.STRING)
    @Comment(value = "주문상태")
    private OrderStatus orderStatus;


    @OneToOne
    @JoinColumn(name = "goods_id")
    @Comment(value = "상품")
    private Goods goods;

    @Comment(value = "주문수량")
    private int orderCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Comment(value = "주문자")
    private User user;

    public OrderItem(int orderPrice, User user) {
        this.orderPrice = orderPrice;
        this.user = user;
        this.orderStatus = OrderStatus.ITEM_SHIPPED;
    }
}
