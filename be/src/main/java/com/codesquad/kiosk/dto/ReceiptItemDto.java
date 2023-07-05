package com.codesquad.kiosk.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReceiptItemDto {
    final String name;
    final Integer quantity;
}
