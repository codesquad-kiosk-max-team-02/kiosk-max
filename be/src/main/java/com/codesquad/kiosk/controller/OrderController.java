package com.codesquad.kiosk.controller;

import com.codesquad.kiosk.dto.PaymentFailedDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codesquad.kiosk.dto.OrderRequestDto;
import com.codesquad.kiosk.dto.ReceiptDto;
import com.codesquad.kiosk.service.OrderService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "결제")
    @PostMapping("api/payments/{method}")
    public ResponseEntity<?> pay(@PathVariable String method, @RequestBody OrderRequestDto orderRequestDto) {
        int amountOfPay = orderService.calculateOrder(orderRequestDto);
        PaymentFailedDto paymentFailedDto = orderService.paymentFail(amountOfPay);
        if(method.equals("cash")) {
            int orderId = orderService.saveOrder(orderRequestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(orderService.paymentResponse(orderId,amountOfPay,orderRequestDto));
        }

        return Optional.ofNullable(paymentFailedDto)
                .<ResponseEntity<?>>map(dto -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto))
                .orElseGet(() -> {
                    int orderId = orderService.saveOrder(orderRequestDto);
                    return ResponseEntity.status(HttpStatus.OK)
                        .body(orderService.paymentResponse(orderId,amountOfPay,orderRequestDto));
                });
    }

    @ApiOperation(value = "개별 주문 상세 조회")
    @GetMapping("api/receipts/{orderId}")
    public ResponseEntity getOrderDetail(@PathVariable Integer orderId){
        ReceiptDto receipt = orderService.getReceiptByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(receipt);
    }
}
