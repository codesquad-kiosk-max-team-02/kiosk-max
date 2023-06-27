package com.codesquad.kiosk.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<String> getCategoryList() {
        String sql = "SELECT NAME FROM CATEGORY";
        return namedParameterJdbcTemplate.query(
                sql,(rs, rowNum) -> rs.getString("NAME")
        );
    }
}
