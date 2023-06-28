package com.codesquad.kiosk.service;

import com.codesquad.kiosk.dto.CategoryResponseDto;
import com.codesquad.kiosk.dto.ReceiptDto;
import com.codesquad.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public ReceiptDto getReceiptByOrderId(Integer orderId) {
        return orderRepository.getReceiptByOrderId(orderId);
    }
}
