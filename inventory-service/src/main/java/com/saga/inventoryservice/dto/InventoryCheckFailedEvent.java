package com.saga.inventoryservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class InventoryCheckFailedEvent {
    private Integer orderId;
    private Integer customerId;
    private List<OrderItem> failedItems;
}
