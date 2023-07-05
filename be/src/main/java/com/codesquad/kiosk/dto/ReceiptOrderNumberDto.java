package com.codesquad.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiptOrderNumberDto {
	final Integer orderNumber;
	final String name;
	final Integer quantity;
}
