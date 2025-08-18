package com.saga.orchestoratorservice.consumer;

import com.saga.orchestoratorservice.config.KafkaProperties;
import com.saga.orchestoratorservice.dto.OrderCreatedEvent;
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

    @KafkaListener(topics = KafkaProperties.orderCreatedTopic, groupId = KafkaProperties.consumerGroupId)
    public void consume(String message){
        try {
            OrderCreatedEvent orderCreatedEvent = this.objectMapperService.readStringToObject(message, OrderCreatedEvent.class);
            this.orchestratorService.handleOrderCreated(orderCreatedEvent);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
