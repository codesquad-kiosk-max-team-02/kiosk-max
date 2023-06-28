package com.codesquad.kiosk.service;

import com.codesquad.kiosk.dto.CategoryResponseDto;
import com.codesquad.kiosk.dto.ReceiptDto;
import com.codesquad.kiosk.domain.Order;
import com.codesquad.kiosk.domain.OrderMenu;
import com.codesquad.kiosk.dto.OrderNumberCreatorDto;
import com.codesquad.kiosk.dto.OrderRequestDto;
import com.codesquad.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public void saveOrder(OrderRequestDto orderRequestDto) {
        String now = createNowDateformat();
        Order order = orderRepository.getOrder().orElse(Order.builder().orderTime(now).orderNumber(0).build());
        OrderNumberCreatorDto dto = new OrderNumberCreatorDto(order.getOrderTime(),order.getOrderNumber());
        for(int i = 0; i<orderRequestDto.getOrderList().size(); i++){
            OrderMenu orderMenu = OrderMenu.builder()
                    .menuId(orderRequestDto.getOrderList().get(i).getMenuId())
                    .quantity(orderRequestDto.getOrderList().get(i).getQuantity())
                    .build();
            orderRepository.saveOrder(createOrderNumber(dto,now),orderMenu,orderRequestDto.getOrderList().get(i).getOption());
        }
    }



    private int createOrderNumber(OrderNumberCreatorDto dto,String now) {
        String storedDate = dto.getDate();
        int orderNumber = dto.getOrderNumber();
        String currentDate = storedDate.split(" ")[0];
        if(!now.equals(currentDate)) {
            return 1;
        }
        return orderNumber+1;
    }

    private String createNowDateformat() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();
        String formattedDate = date.format(formatter);
        return formattedDate;
    }
  
    public ReceiptDto getReceiptByOrderId(Integer orderId) {
        return orderRepository.getReceiptByOrderId(orderId);
    }
}
