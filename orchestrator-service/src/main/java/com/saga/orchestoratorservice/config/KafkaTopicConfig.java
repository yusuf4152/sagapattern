package com.saga.orchestoratorservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public NewTopic orderCreatedTopic() {
        return TopicBuilder.name(this.kafkaProperties.getTopic().getOrderCreated()).build();
    }

    @Bean
    public NewTopic PaymentStartedTopic() {
        return TopicBuilder.name(this.kafkaProperties.getTopic().getPaymentStarted()).build();
    }

    @Bean
    public NewTopic CheckInventoryTopic() {
        return TopicBuilder.name(this.kafkaProperties.getTopic().getCheckInventory()).build();
    }

    @Bean
    public NewTopic inventoryCheckSucceedTopic() {
        return TopicBuilder.name(this.kafkaProperties.getTopic().getInventoryCheckSucceed()).build();
    }

}
