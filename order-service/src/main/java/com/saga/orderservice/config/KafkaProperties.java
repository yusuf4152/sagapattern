package com.saga.orderservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.kafka")
public class KafkaProperties {

    private String bootstrapServers;
    private Consumer consumer;
    private Topic topic;

    @Data
    public static class Consumer {
        private String groupId;
    }

    @Data
    public static class Topic {
        private String orderCreated;
    }
}
