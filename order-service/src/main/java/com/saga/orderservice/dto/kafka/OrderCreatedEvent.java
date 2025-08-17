package com.saga.orderservice.dto.kafka;

import com.saga.orderservice.entity.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent (

        @NotNull(message = "Customer ID is required")
        @Positive(message = "Customer ID must be positive")
        Integer customerId,

        @NotNull(message = "Total amount is required")
        @Positive(message = "Total amount must be positive")
        BigDecimal totalAmount,

        @NotEmpty(message = "Order items cannot be empty")
        List<OrderItem> orderItems

) {
}
