package kr.co.entity;

import jakarta.persistence.*;
import kr.co.config.BooleanConverter;
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
    private String orderItemId;

    @Comment(value = "주문금액")
    private int orderPrice;

    @Comment(value = "주문상태")
    private String orderStatus;

    @Column(columnDefinition = "varchar(1) default 'N'")
    @Convert(converter = BooleanConverter.class)
    private boolean delYn;

    @ManyToOne
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
        this.orderStatus = "배송완료";
    }

    public void updateOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void deleteOrderItem() {
        this.delYn = true;
    }
}
