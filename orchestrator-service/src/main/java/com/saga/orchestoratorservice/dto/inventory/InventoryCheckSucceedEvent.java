package com.saga.orchestoratorservice.dto.inventory;

import com.saga.orchestoratorservice.dto.OrderItem;
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
