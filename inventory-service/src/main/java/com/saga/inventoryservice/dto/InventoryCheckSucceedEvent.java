package com.saga.inventoryservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InventoryCheckSucceedEvent {
    private Integer customerId;
    private Integer orderId;
    private List<OrderItem> checkedItems;
    private BigDecimal amount;
}
