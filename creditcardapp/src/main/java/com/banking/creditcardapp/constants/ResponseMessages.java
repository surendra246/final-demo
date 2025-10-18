package com.banking.creditcardapp.constants;

import java.math.BigDecimal;

public class ResponseMessages {

    // Success messages
    public static final String APPLICATION_SUBMITTED = "Application submitted successfully";
    public static final String CUSTOMER_CREATED = "Customer created successfully";

    // Duplicate messages
    public static final String DUPLICATE_EMAIL = "Email already exists";
    public static final String DUPLICATE_PHONE = "Phone number already exists";

     // Logging
    public static final String CHECKING_EMAIL = "Checking for existing customer with email: {}";
    public static final String CHECKING_PHONE = "Checking for existing customer with phone: {}";
    public static final String DUPLICATE_EMAIL_LOG = "Duplicate email detected: {}";
    public static final String DUPLICATE_PHONE_LOG = "Duplicate phone detected: {}";

    // Validation messages
    public static final String INVALID_INPUT = "Invalid input data";
    public static final String VALIDATION_FAILED = "Validation failed";

    // Error messages
    public static final String UNEXPECTED_ERROR = "Unexpected error occurred";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";

    // Kafka
    public static final String KAFKA_RECEIVED = "Received Kafka message: Application ID = {}, Status = {}, Remarks = {}";
    public static final String APPLICATION_NOT_FOUND = "Application not found for ID: {}";

    // Application
    public static final String APPLICATION_UPDATED = "Application status updated to {} for ID: {}";
    public static final String CARD_ISSUED = "Credit card issued for customer: {}";
    public static final String CARD_NOT_ISSUED = "Credit card not issued for {}: Salary below threshold.";

    public static final BigDecimal FIXED_CREDIT_LIMIT = BigDecimal.valueOf(250000);
}
