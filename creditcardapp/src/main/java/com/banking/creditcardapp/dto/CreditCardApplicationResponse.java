package com.banking.creditcardapp.dto;

import lombok.Data;

@Data
public class CreditCardApplicationResponse {
    private int applicationId;
    private String status;
    private String message;
}
