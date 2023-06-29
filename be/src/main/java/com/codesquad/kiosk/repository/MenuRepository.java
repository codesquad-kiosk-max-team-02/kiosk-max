package com.codesquad.kiosk.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.codesquad.kiosk.domain.Menu;
import com.codesquad.kiosk.domain.OrderMenu;
import com.codesquad.kiosk.dto.MenuDetailDto;
import com.codesquad.kiosk.dto.OptionCategoryDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MenuRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<String> getCategoryList() {
        String sql = "SELECT NAME FROM CATEGORY";
        return namedParameterJdbcTemplate.query(
                sql, (rs, rowNum) -> rs.getString("NAME")
        );
    }

	public List<Menu> getMenuList(Integer categoryId) {
		String sql = "SELECT NAME, PRICE, ID, IMG FROM MENU WHERE CATEGORY_ID = :categoryId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryId", categoryId);
		return namedParameterJdbcTemplate.query(sql, param, menuRowMapper());
	}

	public Optional<OrderMenu> getPopularityRanking(Integer categoryId) {
		String sql = "SELECT MENU_ID, SUM(QUANTITY) AS SUM_QUANTITY FROM ORDER_MENU "
			+ "INNER JOIN MENU ON ORDER_MENU.MENU_ID = MENU.ID "
			+ "INNER JOIN ORDERS ON ORDER_MENU.ORDER_ID = ORDERS.ID "
			+ "WHERE MENU.CATEGORY_ID = :categoryId AND "
			+ "DATE_FORMAT(ORDER_TIME, '%Y-%m-%d') = DATE_FORMAT(:now, '%Y-%m-%d') "
			+ "GROUP BY MENU_ID ORDER BY SUM_QUANTITY DESC";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("categoryId", categoryId)
			.addValue("now", LocalDate.now());
		return namedParameterJdbcTemplate.query(sql, param, orderMenuRowMapper()).stream().findFirst();
	}

	private RowMapper<Menu> menuRowMapper() {
		return (rs, rowNum) -> Menu.builder()
			.id(rs.getInt("id"))
			.name(rs.getString("name"))
			.price(rs.getInt("price"))
			.img(rs.getString("img"))
			.build();
	}

	private RowMapper<OrderMenu> orderMenuRowMapper() {
		return (rs, rowNum) -> OrderMenu.builder()
			.menuId(rs.getInt("menu_id"))
			.quantity(rs.getInt("sum_quantity"))
			.build();
	}
  
  public MenuDetailDto getMenuDetail(int menuId) {
        MenuDetailDto menuDetailDto = getMenu(menuId);
        if (menuDetailDto != null) {
            List<OptionCategoryDto> optionCategories = getOptionCategories(menuId);
            menuDetailDto.setOption(optionCategories);
        }
        return menuDetailDto;
    }

    private MenuDetailDto getMenu(int menuId) {
        String query = "SELECT m.NAME, m.PRICE, m.IMG " +
                "FROM MENU m " +
                "WHERE m.ID = :menuId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("menuId", menuId);

        RowMapper<MenuDetailDto> rowMapper = (resultSet, rowNum) -> {
            MenuDetailDto menuDetailDto = new MenuDetailDto();
            menuDetailDto.setName(resultSet.getString("NAME"));
            menuDetailDto.setPrice(resultSet.getInt("PRICE"));
            menuDetailDto.setImg(resultSet.getString("IMG"));
            return menuDetailDto;
        };

        List<MenuDetailDto> menuDetailList = namedParameterJdbcTemplate.query(query, params, rowMapper);
        return menuDetailList.isEmpty() ? null : menuDetailList.get(0);
    }

    private List<OptionCategoryDto> getOptionCategories(int menuId) {
        String query = "SELECT oc.NAME AS OPTION_CATEGORY_NAME, o.ID AS OPTION_ID, o.NAME AS OPTION_NAME, o.PRICE AS OPTION_PRICE " +
                "FROM MENU_OPTION mo " +
                "JOIN OPTIONS o ON o.ID = mo.OPTION_ID " +
                "JOIN OPTION_CATEGORY oc ON oc.ID = o.OPTION_CATEGORY_ID " +
                "WHERE mo.MENU_ID = :menuId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("menuId", menuId);

        RowMapper<OptionCategoryDto> rowMapper = (resultSet, rowNum) -> {
            OptionCategoryDto optionCategoryDto = new OptionCategoryDto();
            optionCategoryDto.setOptionCategoryType(resultSet.getString("OPTION_CATEGORY_NAME"));
            optionCategoryDto.setOptionId(resultSet.getInt("OPTION_ID"));
            optionCategoryDto.setOptionName(resultSet.getString("OPTION_NAME"));
            optionCategoryDto.setOptionPrice(resultSet.getInt("OPTION_PRICE"));
            return optionCategoryDto;
        };

        return namedParameterJdbcTemplate.query(query, params, rowMapper);
    }
}
