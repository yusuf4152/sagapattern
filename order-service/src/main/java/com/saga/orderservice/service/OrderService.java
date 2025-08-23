package com.saga.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.orderservice.config.KafkaProperties;
import com.saga.orderservice.dto.CreateOrderRequest;
import com.saga.orderservice.dto.kafka.OrderCreatedEvent;
import com.saga.orderservice.entity.Order;
import com.saga.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate kafkaTemplate;

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

}
