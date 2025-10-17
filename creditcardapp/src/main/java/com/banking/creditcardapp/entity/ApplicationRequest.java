package com.banking.creditcardapp.entity;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ApplicationRequest {
    @Id @GeneratedValue
    private Integer id;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private String remarks;
    private Timestamp applicationDate;
    private Timestamp createdAt;
}
