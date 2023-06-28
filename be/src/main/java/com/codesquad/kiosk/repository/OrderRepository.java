package com.codesquad.kiosk.repository;

import com.codesquad.kiosk.dto.ReceiptDto;
import com.codesquad.kiosk.dto.ReceiptItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.codesquad.kiosk.domain.Order;
import com.codesquad.kiosk.domain.OrderMenu;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Order> getOrder() {
        String sql = "SELECT ORDER_TIME, ORDER_NUMBER FROM ORDERS WHERE id = (SELECT MAX(id) FROM ORDERS)";
        List<Order> list = namedParameterJdbcTemplate.query(sql,orderRowMapper());
        return list.stream().findFirst();
    }

    @Transactional
    public void saveOrder(int orderNumber, OrderMenu orderMenu, int[] optionList) {
        // Orders Insert
        String insertOrderQuery = "INSERT INTO ORDERS(ORDER_NUMBER) VALUES(:order_number)";
        SqlParameterSource orderParameters = new MapSqlParameterSource("order_number", orderNumber);
        KeyHolder orderKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertOrderQuery, orderParameters, orderKeyHolder);
        int orderId = orderKeyHolder.getKey().intValue();

        // OrderMenu Insert
        String insertMenuOrderQuery = "INSERT INTO ORDER_MENU(ORDER_ID, MENU_ID, QUANTITY) VALUES(:order_id, :menu_id, :quantity)";
        SqlParameterSource menuOrderParameters = new MapSqlParameterSource()
                .addValue("order_id", orderId)
                .addValue("menu_id", orderMenu.getMenuId())
                .addValue("quantity", orderMenu.getQuantity());
        namedParameterJdbcTemplate.update(insertMenuOrderQuery, menuOrderParameters);

        // Option Insert

        String insertOrderMenuOptionQuery = "INSERT INTO ORDER_MENU_OPTION(ORDER_MENU_ID, OPTION_ID) VALUES(:order_id, :option_id)";
        for(int i = 0 ; i<optionList.length; i++){
            SqlParameterSource orderMenuOptionParameters = new MapSqlParameterSource()
                    .addValue("order_id",orderId)
                    .addValue("option_id",optionList[i]);
            namedParameterJdbcTemplate.update(insertOrderMenuOptionQuery,orderMenuOptionParameters);
        }

    }

    private RowMapper<Order> orderRowMapper () {
        return (rs, rowNum) ->
                Order.builder()
                     .orderTime(rs.getString("ORDER_TIME"))
                     .orderNumber(rs.getInt("ORDER_NUMBER"))
                     .build();
    }
  
    public ReceiptDto getReceiptByOrderId(Integer orderId) {
        String sql = "SELECT o.order_number, m.name AS menu_name, om.quantity" +
                "FROM orders AS o " +
                "JOIN order_menu AS om ON o.id = om.order_id " +
                "JOIN menu AS m ON om.menu_id = m.id " +
                "WHERE o.id = " + orderId;
        return new ReceiptDto (orderId,
                namedParameterJdbcTemplate.query(
                sql,(rs, rowNum) -> new ReceiptItemDto(
                        rs.getString("menu_name"),
                        rs.getInt("quantity"))
        ));
    }

	public Integer getOptionPrice(int menuId, int optionId) {
        String sql = "SELECT IFNULL(MAX(PRICE), 0) PRICE FROM OPTIONS WHERE ID = :optionId "
            + "AND (SELECT COUNT(OPTION_CATEGORY_ID) FROM OPTIONS "
            + "INNER JOIN MENU_OPTION ON OPTIONS.ID = MENU_OPTION.OPTION_ID "
            + "WHERE MENU_ID = :menuId AND OPTION_CATEGORY_ID = (SELECT OPTION_CATEGORY_ID FROM OPTIONS "
            + "INNER JOIN MENU_OPTION ON OPTIONS.ID = MENU_OPTION.OPTION_ID "
            + "WHERE  MENU_OPTION.MENU_ID = :menuId AND MENU_OPTION.OPTION_ID = :optionId)) > 1";
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("menuId", menuId)
            .addValue("optionId", optionId);
        return namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
	}

    public Integer getMenuPrice(int menuId) {
        String sql = "SELECT PRICE FROM MENU WHERE ID = :menuId";
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("menuId", menuId);
        return namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
    }
}
