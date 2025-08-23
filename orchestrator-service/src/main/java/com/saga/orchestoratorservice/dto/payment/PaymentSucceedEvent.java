package com.saga.orchestoratorservice.dto.payment;

import com.saga.orchestoratorservice.dto.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PaymentSucceedEvent {
    private Integer orderId;
    private Integer customerId;
    private BigDecimal amount;
    private String paymentTransactionId;
    private List<OrderItem> orderItems;
}
