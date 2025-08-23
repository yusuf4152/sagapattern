package com.saga.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PaymentSuccessEvent {
    private Integer orderId;
    private Integer customerId;
    private BigDecimal amount;
    private String paymentTransactionId;
    private List<OrderItem> orderItems;
}
