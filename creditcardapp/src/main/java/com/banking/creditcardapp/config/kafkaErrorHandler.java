package com.banking.creditcardapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;

@Configuration
public class kafkaErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(kafkaErrorHandler.class);
    @Bean
    public DefaultErrorHandler errorHandler() {
        return new DefaultErrorHandler((record, exception) -> {
            log.error("Kafka deserialization error: {}", exception.getMessage());
        });
    }
    
}
