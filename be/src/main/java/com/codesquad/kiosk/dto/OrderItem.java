package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int menuId;

    private int quantity;

    private int[] option;
}
