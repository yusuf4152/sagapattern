package com.saga.orderservice.dto;

public record OrderItem(
        Long productId,
        Integer quantity
) {}
