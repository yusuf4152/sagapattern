package com.saga.orchestoratorservice.dto;

public record OrderItem(
        Long productId,
        Integer quantity
) {
}