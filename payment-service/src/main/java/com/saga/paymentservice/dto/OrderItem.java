package com.saga.paymentservice.dto;

import lombok.Data;

@Data
public class OrderItem {
    private Integer productId;
    private Integer quantity;
}
