package com.banking.creditcardapp.service;

import org.apache.kafka.clients.consumer.internals.events.ApplicationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConsumer {
    @KafkaListener(topics = "creditcard_application", groupId = "creditcard-group")
    public void consume(ApplicationEvent event) {
        // Approve or reject logic
        // Update ApplicationRequest entity
    }
}

