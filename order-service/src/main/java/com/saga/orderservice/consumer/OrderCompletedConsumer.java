package com.saga.orderservice.consumer;

import com.saga.orderservice.dto.kafka.OrderCancelledEvent;
import com.saga.orderservice.dto.kafka.OrderCompletedEvent;
import com.saga.orderservice.service.ObjectMapperService;
import com.saga.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCompletedConsumer {

    private final ObjectMapperService objectMapperService;
    private final OrderService orderService;

    @KafkaListener(topics = "${app.kafka.topic.order-completed}", groupId = "${app.kafka.consumer.group-id}")
    public void consumeOrderCompleted(String message){
        OrderCompletedEvent orderCompletedEvent = this.objectMapperService.readStringToObject(message, OrderCompletedEvent.class);
        this.orderService.completeOrder(orderCompletedEvent);
    }
}
