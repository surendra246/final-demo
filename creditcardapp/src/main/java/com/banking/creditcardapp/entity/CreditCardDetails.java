package com.banking.creditcardapp.entity;

import java.math.BigDecimal;
import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CreditCardDetails {
    @Id @GeneratedValue
    private Integer id;
    @ManyToOne
    private Customer customer;
    @Column(length = 16, unique = true, nullable = false)
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    @Column(length = 3, nullable = false)
    private String cvv;
    private BigDecimal limitAmount;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
}

