package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderRequestDto {

    private List<OrderItem> orderList;
    private int number;
}
