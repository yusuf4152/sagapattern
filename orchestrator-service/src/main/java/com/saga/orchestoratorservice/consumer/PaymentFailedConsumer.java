package com.saga.orchestoratorservice.consumer;

import com.saga.orchestoratorservice.dto.payment.PaymentFailedEvent;
import com.saga.orchestoratorservice.service.ObjectMapperService;
import com.saga.orchestoratorservice.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFailedConsumer {

    private final ObjectMapperService objectMapperService;
    private final OrchestratorService orchestratorService;
    @KafkaListener(topics = "${app.kafka.topic.payment-failed}", groupId = "${app.kafka.topic.payment-failed}")
    public void consumePaymentFailed(String message){
      PaymentFailedEvent paymentFailedEvent = this.objectMapperService.readStringToObject(message, PaymentFailedEvent.class);
      orchestratorService.handlePaymentFailed(paymentFailedEvent);
    }
}
