package com.banking.creditcardapp.entity;

import java.time.LocalDateTime;

import com.banking.creditcardapp.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or SEQUENCE
    private Long id;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private String remarks;
    private LocalDateTime applicationDate;
    private LocalDateTime createdAt;
}


