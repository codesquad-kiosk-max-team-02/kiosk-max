package com.codesquad.kiosk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class OptionCategoryDto {
    private String optionCategoryType;
    private int optionId;
    private String optionName;
    private int optionPrice;
}
