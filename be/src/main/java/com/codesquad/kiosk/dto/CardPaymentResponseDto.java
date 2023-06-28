package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardPaymentResponseDto{
    Integer orderNumber;
    Integer totalPay;
    Integer cardNumber;
    Boolean result;

}
