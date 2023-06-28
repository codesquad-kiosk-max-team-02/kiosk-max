package com.codesquad.kiosk.dto;
import com.codesquad.kiosk.domain.OrderMenu;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReceiptDto {
    final Integer orderNumber;
    final List<ReceiptItemDto> orderList;
}
