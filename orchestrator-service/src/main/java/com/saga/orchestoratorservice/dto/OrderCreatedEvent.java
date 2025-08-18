package com.saga.orchestoratorservice.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent (
        Integer customerId,
        BigDecimal totalAmount,
        List<OrderItem> orderItems
) {
}
