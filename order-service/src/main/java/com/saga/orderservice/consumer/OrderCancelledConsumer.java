package com.saga.orderservice.consumer;

import com.saga.orderservice.dto.kafka.OrderCancelledEvent;
import com.saga.orderservice.service.ObjectMapperService;
import com.saga.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCancelledConsumer {

    private final ObjectMapperService objectMapperService;
    private final OrderService orderService;

    @KafkaListener(topics = "${app.kafka.topic.order-cancelled}", groupId = "${app.kafka.consumer.group-id}")
    public void consumeOrderCancelled(String message){
       OrderCancelledEvent orderCancelledEvent = this.objectMapperService.readStringToObject(message, OrderCancelledEvent.class);
       this.orderService.cancelOrder(orderCancelledEvent);
    }
}
