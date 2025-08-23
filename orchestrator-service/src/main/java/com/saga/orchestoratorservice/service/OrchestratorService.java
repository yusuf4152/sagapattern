package com.saga.orchestoratorservice.service;

import com.saga.orchestoratorservice.config.KafkaProperties;
import com.saga.orchestoratorservice.dto.inventory.InventoryCheckEvent;
import com.saga.orchestoratorservice.dto.inventory.InventoryCheckFailedEvent;
import com.saga.orchestoratorservice.dto.inventory.InventoryCheckSucceedEvent;
import com.saga.orchestoratorservice.dto.order.OrderCancelledEvent;
import com.saga.orchestoratorservice.dto.order.OrderCompletedEvent;
import com.saga.orchestoratorservice.dto.order.OrderCreatedEvent;
import com.saga.orchestoratorservice.dto.payment.PaymentFailedEvent;
import com.saga.orchestoratorservice.dto.payment.PaymentStartedEvent;
import com.saga.orchestoratorservice.dto.payment.PaymentSucceedEvent;
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

    public void handleInventoryCheckFailed(InventoryCheckFailedEvent inventoryCheckFailedEvent){
        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent();
        orderCancelledEvent.setOrderId(inventoryCheckFailedEvent.getOrderId());
        this.kafkaTemplate.send(this.kafkaProperties.getTopic().getOrderCancelled(), orderCancelledEvent);
    }

    public void handlePaymentFailed(PaymentFailedEvent paymentFailedEvent){
        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent();
        orderCancelledEvent.setOrderId(paymentFailedEvent.getOrderId());
        this.kafkaTemplate.send(this.kafkaProperties.getTopic().getOrderCancelled(), orderCancelledEvent);
    }

    public void handlePaymentSucceed(PaymentSucceedEvent paymentSucceedEvent){
        OrderCompletedEvent orderCompletedEvent = new OrderCompletedEvent();
        orderCompletedEvent.setOrderId(paymentSucceedEvent.getOrderId());
        this.kafkaTemplate.send(this.kafkaProperties.getTopic().getOrderCompleted(), orderCompletedEvent);
    }

}
