package com.banking.creditcardapp.entity;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.transaction.Status;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;
    private String panCard;
    private String aadharCard;
    private String address;
    private LocalDate dob;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
}
