package com.saga.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.orderservice.config.KafkaProperties;
import com.saga.orderservice.dto.CreateOrderRequest;
import com.saga.orderservice.dto.OrderStatus;
import com.saga.orderservice.dto.kafka.OrderCancelledEvent;
import com.saga.orderservice.dto.kafka.OrderCompletedEvent;
import com.saga.orderservice.dto.kafka.OrderCreatedEvent;
import com.saga.orderservice.entity.Order;
import com.saga.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void createOrder(CreateOrderRequest createOrderRequest) {
        try {
            Order order = this.objectMapper.convertValue(createOrderRequest, Order.class);
            Order savedOrder = this.orderRepository.save(order);
            OrderCreatedEvent event = new OrderCreatedEvent(
                    savedOrder.getCustomerId(),
                    new BigDecimal(savedOrder.getAmount()),
                    savedOrder.getOrderItems()
            );
            this.kafkaTemplate.send(kafkaProperties.getTopic().getOrderCreated(), event);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void cancelOrder(OrderCancelledEvent orderCancelledEvent) {
        Optional<Order> orderOpt = this.orderRepository.findById(orderCancelledEvent.orderId().intValue());
        if (orderOpt.isPresent()){
            Order order = orderOpt.get();
            order.setStatus(OrderStatus.CANCELLED);
            this.orderRepository.save(orderOpt.get());
        }
    }

    public void completeOrder(OrderCompletedEvent orderCompletedEvent) {
        Optional<Order> orderOpt = this.orderRepository.findById(orderCompletedEvent.orderId().intValue());
        if (orderOpt.isPresent()){
            Order order = orderOpt.get();
            order.setStatus(OrderStatus.COMPLETED);
            this.orderRepository.save(orderOpt.get());
        }
    }
}
