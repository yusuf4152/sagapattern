package com.saga.orchestoratorservice.dto.order;

import com.saga.orchestoratorservice.dto.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent (
        Integer orderId,
        Integer customerId,
        BigDecimal totalAmount,
        List<OrderItem> orderItems
) {
}
