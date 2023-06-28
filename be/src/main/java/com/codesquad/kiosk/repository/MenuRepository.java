package com.codesquad.kiosk.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.codesquad.kiosk.domain.Menu;
import com.codesquad.kiosk.domain.OrderMenu;

import lombok.RequiredArgsConstructor;

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

	public List<Menu> getMenuList(Integer categoryId) {
		String sql = "SELECT NAME, PRICE, ID, IMG FROM MENU WHERE CATEGORY_ID = :categoryId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryId", categoryId);
		return namedParameterJdbcTemplate.query(sql, param, menuRowMapper());
	}

	public List<OrderMenu> getPopularityRanking(Integer categoryId) {
		String sql = "SELECT MENU_ID, SUM(QUANTITY) AS SUM_QUANTITY FROM ORDER_MENU "
			+ "INNER JOIN MENU ON ORDER_MENU.MENU_ID = MENU.ID "
			+ "INNER JOIN ORDERS ON ORDER_MENU.ORDER_ID = ORDERS.ID "
			+ "WHERE MENU.CATEGORY_ID = :categoryId AND "
			+ "DATE_FORMAT(ORDER_TIME, '%Y-%m-%d') = DATE_FORMAT(:now, '%Y-%m-%d') "
			+ "GROUP BY MENU_ID ORDER BY SUM_QUANTITY DESC";
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("categoryId", categoryId)
			.addValue("now", LocalDate.now());
		return namedParameterJdbcTemplate.query(sql, param, orderMenuRowMapper());
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
}
