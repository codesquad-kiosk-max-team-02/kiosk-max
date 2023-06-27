package com.codesquad.kiosk.service;

import com.codesquad.kiosk.dto.CategoryResponseDto;
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
}
