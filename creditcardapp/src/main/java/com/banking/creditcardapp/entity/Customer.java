package com.banking.creditcardapp.entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    @NotBlank(message = "Phone number is required")
    @Column(unique = true, nullable = false)
    private String phone;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format")
    private String panCard;

    @Pattern(regexp = "\\d{12}", message = "Aadhar must be 12 digits")
    private String aadharCard;

    @NotBlank(message = "Address is required")
    private String address;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be positive")
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
}
