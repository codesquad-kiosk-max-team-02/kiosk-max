package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem {
    private int menuId;

    private int quantity;

    private int[] option;
}
