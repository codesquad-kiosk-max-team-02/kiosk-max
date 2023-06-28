package com.codesquad.kiosk.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenusByCategoryResponseDto {
	private String name;
	private Integer price;
	private Integer menuId;
	private String img;
	private boolean popular;

	public void setPopular() {
		popular = true;
	}
}
