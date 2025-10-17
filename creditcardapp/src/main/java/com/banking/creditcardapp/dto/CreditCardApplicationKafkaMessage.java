package com.banking.creditcardapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardApplicationKafkaMessage {
    private Long applicationId;
    private String status;
    private String remarks;
}

