package com.saga.orderservice.dto;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest (
        Integer customerId,
        BigDecimal amount,
        List<OrderItem> orderItems
) {}
