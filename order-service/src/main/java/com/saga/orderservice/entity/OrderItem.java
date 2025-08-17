package com.saga.orderservice.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class OrderItem {
    private Integer productId;
    private BigDecimal quantity;
}