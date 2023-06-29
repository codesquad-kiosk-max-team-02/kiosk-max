package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {

    private boolean result;
    private int changes;
    private int totalPay;
    private int orderId;

}
