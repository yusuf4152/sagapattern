package com.saga.orchestoratorservice.consumer;

import com.saga.orchestoratorservice.dto.payment.PaymentSucceedEvent;
import com.saga.orchestoratorservice.service.ObjectMapperService;
import com.saga.orchestoratorservice.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSucceedConsumer {

    private final ObjectMapperService objectMapperService;
    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "${app.kafka.topic.payment-success}", groupId = "${app.kafka.consumer.group-id}")
    private void handlePaymentSuccess(String message){
        try {
            PaymentSucceedEvent paymentSucceedEvent = this.objectMapperService.readStringToObject(message, PaymentSucceedEvent.class);
            this.orchestratorService.handlePaymentSucceed(paymentSucceedEvent);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
