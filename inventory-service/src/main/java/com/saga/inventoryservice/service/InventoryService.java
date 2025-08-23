package com.saga.inventoryservice.service;

import com.saga.inventoryservice.config.KafkaProperties;
import com.saga.inventoryservice.dto.InventoryCheckEvent;
import com.saga.inventoryservice.dto.InventoryCheckFailedEvent;
import com.saga.inventoryservice.dto.InventoryCheckSucceedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void checkInventory(InventoryCheckEvent inventoryCheckEvent){
        boolean isSuccess = Math.random() > 0.1;
        if (isSuccess) {
            InventoryCheckSucceedEvent inventoryCheckSucceedEvent = new InventoryCheckSucceedEvent();
            inventoryCheckSucceedEvent.setCustomerId(inventoryCheckEvent.getCustomerId());
            inventoryCheckSucceedEvent.setOrderId(inventoryCheckEvent.getOrderId());
            inventoryCheckSucceedEvent.setCheckedItems(inventoryCheckEvent.getOrderItems());
            inventoryCheckSucceedEvent.setAmount(inventoryCheckEvent.getTotalAmount());
            this.kafkaTemplate.send(kafkaProperties.getTopic().getInventoryCheckSucceed(), inventoryCheckSucceedEvent);
        }
        else {
            InventoryCheckFailedEvent inventoryCheckFailedEvent = new InventoryCheckFailedEvent();
            inventoryCheckFailedEvent.setCustomerId(inventoryCheckEvent.getCustomerId());
            inventoryCheckFailedEvent.setOrderId(inventoryCheckEvent.getOrderId());
            inventoryCheckFailedEvent.setFailedItems(inventoryCheckEvent.getOrderItems());
            this.kafkaTemplate.send(kafkaProperties.getTopic().getInventoryCheckFailed(), inventoryCheckFailedEvent);
        }
    }
}
