package com.codesquad.kiosk.dto;

public class PaymentFailedDto {

    private boolean result;

    private String cause;

    public PaymentFailedDto(String cause) {
        this.result = false;
        this.cause = cause;
    }
}