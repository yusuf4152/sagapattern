package com.saga.orchestoratorservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InventoryCheckSucceedEvent {
    private Integer customerId;
    private Integer orderId;
    private List<OrderItem> checkedItems;
    private BigDecimal totalAmount;
}
