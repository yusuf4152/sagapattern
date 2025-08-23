package com.saga.orchestoratorservice.dto.order;

import lombok.Data;

@Data
public class OrderCompletedEvent {
    private Integer orderId;
}