package com.saga.orchestoratorservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PaymentStartedEvent {
    private Integer orderId;
    private Integer customerId;
    private BigDecimal totalAmount;
    List<OrderItem> orderItems;
}
