package com.codesquad.kiosk.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReceiptDto {
    final Integer orderNumber;
    final List<ReceiptItemDto> orderList;
}
