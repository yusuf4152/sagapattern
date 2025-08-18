package com.saga.orchestoratorservice.service;

import com.saga.orchestoratorservice.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestratorService {

    private final KafkaTemplate kafkaTemplate;

    public void handleOrderCreated(OrderCreatedEvent orderCreatedEvent){

    }
}
