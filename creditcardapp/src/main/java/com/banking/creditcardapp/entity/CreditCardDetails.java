package com.banking.creditcardapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CreditCardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or SEQUENCE
    private Long id;
    @ManyToOne
    private Customer customer;
    @Column(length = 16, unique = true, nullable = false)
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    @Column(length = 3, nullable = false)
    private String cvv;
    private BigDecimal limitAmount;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
}

