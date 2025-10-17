package com.banking.creditcardapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CreditCardApplicationRequest {
    private String name;
    private String email;
    private String phone;
    private String panCard;
    private String aadharCard;
    private String address;
    private LocalDate dob;
    private BigDecimal salary;
}

