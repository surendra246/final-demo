package com.banking.creditcardapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CreditCardSearchResponse {
    private String customerName;
    private String cardNumber;
    private String cvv;
    private LocalDate expiryDate;
    private BigDecimal limit;
}

