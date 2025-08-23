package com.saga.orderservice.config;

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
        return TopicBuilder.name(kafkaProperties.getTopic().getOrderCreated()).build();
    };

    @Bean
    public NewTopic orderCompletedTopic() {
        return TopicBuilder.name(kafkaProperties.getTopic().getOrderCompleted()).build();
    };

    @Bean
    public NewTopic orderCancelledTopic() {
        return TopicBuilder.name(kafkaProperties.getTopic().getOrderCancelled()).build();
    };

}
