package com.codesquad.kiosk.repository;

import com.codesquad.kiosk.domain.Order;
import com.codesquad.kiosk.domain.OrderMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    public void saveOrder(int orderNumber) {
        // Orders Insert
        String insertOrderQuery = "INSERT INTO ORDERS(ORDER_NUMBER) VALUES(:order_number)";
        SqlParameterSource orderParameters = new MapSqlParameterSource("order_number", orderNumber);
        KeyHolder orderKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertOrderQuery, orderParameters, orderKeyHolder);
    }

    private RowMapper<Order> orderRowMapper () {
        return (rs, rowNum) ->
                Order.builder()
                     .orderTime(rs.getString("ORDER_TIME"))
                     .orderNumber(rs.getInt("ORDER_NUMBER"))
                     .build();
    }
}
