package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderNumberCreatorDto {
    private String date;
    private int orderNumber;
}
