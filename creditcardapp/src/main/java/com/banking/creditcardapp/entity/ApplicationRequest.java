package com.banking.creditcardapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class ApplicationRequest {
    @Id @GeneratedValue
    private Integer id;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private String remarks;
    private LocalDateTime applicationDate;
    private LocalDateTime createdAt;
}


