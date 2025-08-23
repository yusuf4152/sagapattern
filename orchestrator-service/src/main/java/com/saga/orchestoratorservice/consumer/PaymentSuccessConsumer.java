package com.saga.orchestoratorservice.consumer;

import com.saga.orchestoratorservice.dto.PaymentSuccessEvent;
import com.saga.orchestoratorservice.service.ObjectMapperService;
import com.saga.orchestoratorservice.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSuccessConsumer {

    private final ObjectMapperService objectMapperService;
    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "${app.kafka.topic.payment-success}", groupId = "${app.kafka.consumer.group-id}")
    private void handlePaymentSuccess(String message){
        try {
            PaymentSuccessEvent paymentSuccessEvent = this.objectMapperService.readStringToObject(message, PaymentSuccessEvent.class);
        }catch (Exception e){

        }
    }
}
