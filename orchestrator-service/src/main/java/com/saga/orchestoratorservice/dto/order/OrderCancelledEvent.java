package com.saga.orchestoratorservice.dto.order;

import lombok.Data;

@Data
public class OrderCancelledEvent {
   private Integer orderId;
}

