package com.banking.creditcardapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.banking.creditcardapp.dto.CreditCardApplicationKafkaMessage;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, CreditCardApplicationKafkaMessage> kafkaTemplate;

    public void sendApplicationMessage(CreditCardApplicationKafkaMessage message) {
        kafkaTemplate.send("creditcard_application", message);
    }
}


