package com.saga.orchestoratorservice.consumer;

import com.saga.orchestoratorservice.dto.inventory.InventoryCheckSucceedEvent;
import com.saga.orchestoratorservice.service.ObjectMapperService;
import com.saga.orchestoratorservice.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryCheckSucceedConsumer {

    private final ObjectMapperService objectMapperService;
    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "${app.kafka.topic.inventory-check-succeed}", groupId = "${app.kafka.consumer.group-id}")
    public void  consumeInventoryCheckSucceed(String message){
        try {
            InventoryCheckSucceedEvent inventoryCheckSucceedEvent = this.objectMapperService.readStringToObject(message, InventoryCheckSucceedEvent.class);
            this.orchestratorService.handleInventoryCheckSucceed(inventoryCheckSucceedEvent);
        }catch (Exception e){
                throw new RuntimeException(e.getMessage());
        }
    }
}
