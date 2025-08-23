package com.saga.paymentservice.service;

import com.saga.paymentservice.config.KafkaProperties;
import com.saga.paymentservice.dto.PaymentFailedEvent;
import com.saga.paymentservice.dto.PaymentStartedEvent;
import com.saga.paymentservice.dto.PaymentSucceedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void paymentProcess(PaymentStartedEvent paymentStartedEvent) {
        boolean isSuccess = Math.random()> 0.2;
        if (isSuccess) {
            String paymentTransactionId = UUID.randomUUID().toString();
            PaymentSucceedEvent paymentSuccessEvent = new PaymentSucceedEvent();
            paymentSuccessEvent.setPaymentTransactionId(paymentTransactionId);
            paymentSuccessEvent.setCustomerId(paymentStartedEvent.getCustomerId());
            paymentSuccessEvent.setAmount(paymentStartedEvent.getAmount());
            paymentSuccessEvent.setOrderItems(paymentStartedEvent.getOrderItems());
            paymentSuccessEvent.setOrderId(paymentStartedEvent.getOrderId());
            this.kafkaTemplate.send(kafkaProperties.getTopic().getPaymentSuccess(),paymentSuccessEvent);
        }
        else {
            PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent();
            paymentFailedEvent.setCustomerId(paymentStartedEvent.getCustomerId());
            paymentFailedEvent.setAmount(paymentStartedEvent.getAmount());
            paymentFailedEvent.setOrderItems(paymentStartedEvent.getOrderItems());
            paymentFailedEvent.setOrderId(paymentStartedEvent.getOrderId());
            this.kafkaTemplate.send(kafkaProperties.getTopic().getPaymentFailed(), paymentFailedEvent);
        }
    }
}
