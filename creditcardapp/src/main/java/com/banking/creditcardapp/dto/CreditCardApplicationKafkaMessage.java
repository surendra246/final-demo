package com.banking.creditcardapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardApplicationKafkaMessage {
    private int applicationId;
    private String status;
    private String remarks;
}

