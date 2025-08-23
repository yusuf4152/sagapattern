package com.saga.orderservice.dto.kafka;

public record OrderCompletedEvent(
        Long orderId
) {
}
