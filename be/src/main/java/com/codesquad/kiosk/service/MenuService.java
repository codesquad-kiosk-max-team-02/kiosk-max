package com.codesquad.kiosk.service;

import com.codesquad.kiosk.dto.CategoryResponseDto;
import com.codesquad.kiosk.dto.MenuDetailDto;
import com.codesquad.kiosk.dto.OptionCategoryDto;
import com.codesquad.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<CategoryResponseDto> getCategories() {
        List<String> categories = menuRepository.getCategoryList();
        List<CategoryResponseDto> categoryResponseList = categories.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
        return categoryResponseList;
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
