package com.codesquad.kiosk.dto;

import lombok.Getter;
@Getter
public class PaymentFailedDto {

    private boolean result;

    private String cause;

    public PaymentFailedDto(String cause) {
        this.result = false;
        this.cause = cause;
    }
}