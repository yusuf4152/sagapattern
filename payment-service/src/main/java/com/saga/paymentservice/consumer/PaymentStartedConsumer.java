package com.saga.paymentservice.consumer;


import com.saga.paymentservice.dto.PaymentStartedEvent;
import com.saga.paymentservice.service.ObjectMapperService;
import com.saga.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentStartedConsumer {

    private final ObjectMapperService objectMapperService;
    private final PaymentService paymentService;

    @KafkaListener(topics = "${app.kafka.topic.payment-started}", groupId = "${app.kafka.consumer.group-id}")
    public void consumePaymentStarted(String message) {
        try {
            PaymentStartedEvent paymentStartedEvent = this.objectMapperService.readStringToObject(message, PaymentStartedEvent.class);
            this.paymentService.paymentProcess(paymentStartedEvent);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    };
}
