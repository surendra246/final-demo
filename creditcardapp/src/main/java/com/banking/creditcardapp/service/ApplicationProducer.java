package com.banking.creditcardapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ApplicationProducer {
    @Autowired
    private KafkaTemplate<String, ApplicationEvent> kafkaTemplate;

    public void sendApplicationEvent(ApplicationEvent event) {
        kafkaTemplate.send("creditcard_application", event);
    }
}

