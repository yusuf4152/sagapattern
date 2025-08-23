package com.saga.orchestoratorservice.service;

import com.saga.orchestoratorservice.config.KafkaProperties;
import com.saga.orchestoratorservice.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchestratorService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void handleOrderCreated(OrderCreatedEvent orderCreatedEvent){
        InventoryCheckEvent inventoryCheckEvent = new InventoryCheckEvent();
        inventoryCheckEvent.setOrderId(orderCreatedEvent.orderId());
        inventoryCheckEvent.setCustomerId(orderCreatedEvent.customerId());
        inventoryCheckEvent.setOrderItems(orderCreatedEvent.orderItems());
        inventoryCheckEvent.setTotalAmount(orderCreatedEvent.totalAmount());
        this.kafkaTemplate.send(this.kafkaProperties.getTopic().getCheckInventory(), inventoryCheckEvent);
        log.info("inventory check event sent for order: {}", orderCreatedEvent.orderId());
    }

    public void handleInventoryCheckSucceed(InventoryCheckSucceedEvent inventoryCheckSucceedEvent){
        PaymentStartedEvent paymentStartedEvent = new PaymentStartedEvent();
        paymentStartedEvent.setCustomerId(inventoryCheckSucceedEvent.getCustomerId());
        paymentStartedEvent.setOrderId(inventoryCheckSucceedEvent.getOrderId());
        paymentStartedEvent.setOrderItems(inventoryCheckSucceedEvent.getCheckedItems());
        paymentStartedEvent.setTotalAmount(inventoryCheckSucceedEvent.getTotalAmount());
        this.kafkaTemplate.send(this.kafkaProperties.getTopic().getPaymentStarted(), paymentStartedEvent);
    }

}
