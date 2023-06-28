package com.codesquad.kiosk.controller;

import com.codesquad.kiosk.dto.CategoryResponseDto;
import com.codesquad.kiosk.dto.MenuDetailDto;
import com.codesquad.kiosk.dto.MenusByCategoryResponseDto;
import com.codesquad.kiosk.service.MenuService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @ApiOperation(value = "카테고리별 메뉴 전체 조회")
    @GetMapping("api/menus/{categoryId}")
    public ResponseEntity<List<MenusByCategoryResponseDto>> getMenusByCategory(@PathVariable Integer categoryId) {
        List<MenusByCategoryResponseDto> menus = menuService.getMenusByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(menus);
    }

    @ApiOperation(value = "개별 메뉴 조회")
    @GetMapping("api/carts/{menuId}")
    public ResponseEntity<MenuDetailDto> getMenuDetail(@PathVariable Integer menuId){
        try {
            MenuDetailDto menuDetailDto = menuService.getMenuDetail(menuId);
            return ResponseEntity.status(HttpStatus.OK).body(menuDetailDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @ApiOperation(value = "상단 바 출력")
    @GetMapping("api/categories")
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> categories = menuService.getCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
}
