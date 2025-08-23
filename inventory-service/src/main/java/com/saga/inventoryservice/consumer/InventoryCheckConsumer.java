package com.saga.inventoryservice.consumer;

import com.saga.inventoryservice.dto.InventoryCheckEvent;
import com.saga.inventoryservice.service.InventoryService;
import com.saga.inventoryservice.service.ObjectMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryCheckConsumer {

    private final InventoryService inventoryService;
    private final ObjectMapperService objectMapperService;

    @KafkaListener(topics = "${app.kafka.topic.check-inventory}", groupId = "${app.kafka.consumer.group-id}")
    public void consumeInventoryCheck(String message) {
        try {
            InventoryCheckEvent inventoryCheckEvent = this.objectMapperService.readStringToObject(message, InventoryCheckEvent.class);
            this.inventoryService.checkInventory(inventoryCheckEvent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
