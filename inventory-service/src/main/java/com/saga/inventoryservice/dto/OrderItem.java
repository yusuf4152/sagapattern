package com.saga.inventoryservice.dto;

public record OrderItem(
        Long productId,
        Integer quantity
) {
}