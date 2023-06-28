package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private List<OrderItem> orderList;
    private int number;
}
