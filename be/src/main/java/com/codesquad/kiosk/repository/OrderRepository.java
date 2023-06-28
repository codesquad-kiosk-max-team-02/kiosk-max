package com.codesquad.kiosk.repository;

import com.codesquad.kiosk.dto.ReceiptDto;
import com.codesquad.kiosk.dto.ReceiptItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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



}
