package kr.co.entity;

public enum OrderStatus {
    WAITING_FOR_DEPOSIT,  // 입금 전
    DEPOSIT_CONFIRMED,    // 입금 확인
    ITEM_SHIPPED,         // 상품 발송
    SHIPPING_COMPLETED    // 발송 완료
}
