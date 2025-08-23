package com.saga.orchestoratorservice.dto.inventory;

import com.saga.orchestoratorservice.dto.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class InventoryCheckFailedEvent {
    private Integer orderId;
    private Integer customerId;
    private List<OrderItem> failedItems;
}
