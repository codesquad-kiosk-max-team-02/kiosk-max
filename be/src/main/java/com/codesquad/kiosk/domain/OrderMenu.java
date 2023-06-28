package com.codesquad.kiosk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderMenu {
    Integer id;
    Integer orderId;
    Integer menuId;
    Integer quantity;

    @Builder
    public OrderMenu(Integer menuId, Integer quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }
}
