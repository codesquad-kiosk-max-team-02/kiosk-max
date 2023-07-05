package com.codesquad.kiosk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codesquad.kiosk.domain.Menu;
import com.codesquad.kiosk.domain.OrderMenu;
import com.codesquad.kiosk.dto.CategoryResponseDto;
import com.codesquad.kiosk.dto.MenuDetailDto;
import com.codesquad.kiosk.dto.MenusByCategoryResponseDto;
import com.codesquad.kiosk.dto.OptionCategoryDto;
import com.codesquad.kiosk.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<CategoryResponseDto> getCategories() {
        List<String> categories = menuRepository.getCategoryList();
		return categories.stream()
				.map(CategoryResponseDto::new)
				.collect(Collectors.toList());
    }

	  public List<MenusByCategoryResponseDto> getMenusByCategory(Integer categoryId) {
		List<Menu> menuList = menuRepository.getMenuList(categoryId);
		int menuId = menuRepository.getPopularityRanking(categoryId)
			.orElse(OrderMenu.builder()
				.menuId(0)
				.build())
			.getMenuId();
		List<MenusByCategoryResponseDto> menus = menuList.stream()
			.map(menu -> MenusByCategoryResponseDto.builder()
				.name(menu.getName())
				.price(menu.getPrice())
				.menuId(menu.getId())
				.img(menu.getImg())
				.build())
			.collect(Collectors.toList());
		  for (MenusByCategoryResponseDto menu : menus) {
			  if (menu.getMenuId() == menuId) {
				  menu.setPopular();
			  }
		  }
		return menus;
	}
  
    public MenuDetailDto getMenuDetail(Integer menuId) {
        MenuDetailDto menuDetailDto = menuRepository.getMenuDetail(menuId);
        if (menuDetailDto == null) {
            throw new IllegalArgumentException();
        }
        return updateOptionPrice(menuDetailDto);
    }

    private MenuDetailDto updateOptionPrice(MenuDetailDto menuDetailDto) {
        List<OptionCategoryDto> optionList = menuDetailDto.getOption();

        List<String> optionCategoryTypes = optionList.stream()
                .map(OptionCategoryDto::getOptionCategoryType)
                .collect(Collectors.toList());

        List<OptionCategoryDto> updatedOptionList =  optionList.stream()
                .map(option -> {long count = optionCategoryTypes.stream()
                        .filter(type -> type.equals(option.getOptionCategoryType()))
                        .count();
                if (count == 1) {
                option.setOptionPrice(0);}
                return option;
                })
                .collect(Collectors.toList());
        menuDetailDto.setOption(updatedOptionList);
        return menuDetailDto;
    }
}
