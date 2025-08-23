package com.saga.orchestoratorservice.consumer;

import com.saga.orchestoratorservice.dto.inventory.InventoryCheckFailedEvent;
import com.saga.orchestoratorservice.service.ObjectMapperService;
import com.saga.orchestoratorservice.service.OrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryCheckFailedConsumer {

    private final ObjectMapperService objectMapperService;
    private final OrchestratorService orchestratorService;

    @KafkaListener(topics = "${app.kafka.topic.invetory-check-failed}", groupId = "${app.kafka.consumer.group-id}")
    public void inventoryCheckFailedConsumer(String message) {
        try {
            InventoryCheckFailedEvent inventoryCheckFailedEvent = this.objectMapperService.readStringToObject(message, InventoryCheckFailedEvent.class);
            this.orchestratorService.handleInventoryCheckFailed(inventoryCheckFailedEvent);
        }catch (Exception e) {

        }
    }
}
