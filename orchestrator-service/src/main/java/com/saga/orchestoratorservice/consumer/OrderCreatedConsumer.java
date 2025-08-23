package com.saga.orchestoratorservice.consumer;

import com.saga.orchestoratorservice.dto.order.OrderCreatedEvent;
import com.saga.orchestoratorservice.service.ObjectMapperService;
import com.saga.orchestoratorservice.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final OrchestratorService orchestratorService;
    private final ObjectMapperService objectMapperService;

    @KafkaListener(topics = "${app.kafka.topic.order-created}", groupId = "${app.kafka.consumer.group-id}")
    public void consume(String message){
        try {
            OrderCreatedEvent orderCreatedEvent = this.objectMapperService.readStringToObject(message, OrderCreatedEvent.class);
            this.orchestratorService.handleOrderCreated(orderCreatedEvent);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
