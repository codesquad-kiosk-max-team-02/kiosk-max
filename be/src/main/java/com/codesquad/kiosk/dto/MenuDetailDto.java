package com.codesquad.kiosk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class MenuDetailDto {

    private String name;
    private int price;
    private String img;
    private List<OptionCategoryDto> option;
}

