package com.codesquad.kiosk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Order {
    Integer id;
    Integer orderNumber;
    String orderTime;

    @Builder
    public Order(Integer orderNumber, String orderTime) {
        this.orderNumber = orderNumber;
        this.orderTime = orderTime;
    }
}
