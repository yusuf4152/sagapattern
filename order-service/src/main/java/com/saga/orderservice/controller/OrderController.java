package com.saga.orderservice.controller;

import com.saga.orderservice.dto.CreateOrderRequest;
import com.saga.orderservice.service.OrderService;
import com.saga.orderservice.util.ApiVersionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiVersionUtil.API_VERSION+ "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        this.orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
