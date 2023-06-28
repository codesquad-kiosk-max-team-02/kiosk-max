package com.codesquad.kiosk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Menu {
    Integer id;
    String name;
    Integer categoryId;
    Integer price;
    String img;
}
