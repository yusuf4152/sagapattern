package com.saga.orderservice.dto.kafka;

public record OrderCancelledEvent(
        Long orderId
) {
}
