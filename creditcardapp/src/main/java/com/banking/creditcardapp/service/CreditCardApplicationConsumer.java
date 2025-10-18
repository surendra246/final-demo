package com.banking.creditcardapp.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.banking.creditcardapp.constants.ResponseMessages;
import com.banking.creditcardapp.dto.CreditCardApplicationKafkaMessage;
import com.banking.creditcardapp.entity.ApplicationRequest;
import com.banking.creditcardapp.entity.CreditCardDetails;
import com.banking.creditcardapp.entity.Customer;
import com.banking.creditcardapp.entity.CustomerStatus;
import com.banking.creditcardapp.enums.ApplicationStatus;
import com.banking.creditcardapp.repository.ApplicationRequestRepository;
import com.banking.creditcardapp.repository.CreditCardDetailsRepository;
import com.banking.creditcardapp.repository.CustomerRepository;

@Service
public class CreditCardApplicationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardApplicationConsumer.class);

    private final ApplicationRequestRepository applicationRepo;
    private final CreditCardDetailsRepository cardRepo;
    private final CustomerRepository customerRepo;

    public CreditCardApplicationConsumer(ApplicationRequestRepository applicationRepo,
                                         CreditCardDetailsRepository cardRepo, CustomerRepository customerRepo) {
        this.applicationRepo = applicationRepo;
        this.cardRepo = cardRepo;
        this.customerRepo = customerRepo;
    }

    @KafkaListener(topics = "creditcard_application", groupId = "creditcard-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeApplication(CreditCardApplicationKafkaMessage message) {
        logger.info(ResponseMessages.KAFKA_RECEIVED, message.getApplicationId(), message.getStatus(), message.getRemarks());

        Optional<ApplicationRequest> optionalRequest = applicationRepo.findById(message.getApplicationId());
        if (optionalRequest.isEmpty()) {
            logger.warn(ResponseMessages.APPLICATION_NOT_FOUND, message.getApplicationId());
            return;
        }

        ApplicationRequest request = optionalRequest.get();
        Customer customer = request.getCustomer();

        // Auto-approve if salary > 25,000
        if (customer.getSalary().compareTo(BigDecimal.valueOf(25000)) >= 0) {
            request.setStatus(ApplicationStatus.APPROVED);
            request.setRemarks("Auto-approved based on salary");
            applicationRepo.save(request);
            logger.info(ResponseMessages.APPLICATION_UPDATED, "APPROVED", message.getApplicationId());

            issueCreditCard(customer);
        } else {
            request.setStatus(ApplicationStatus.REJECTED);
            request.setRemarks("Salary below approval threshold");

            customer.setStatus(CustomerStatus.INACTIVE);
            customerRepo.save(customer);
            applicationRepo.save(request);
            logger.info(ResponseMessages.APPLICATION_UPDATED, "REJECTED", message.getApplicationId());
        }
    }

    private void issueCreditCard(Customer customer) {
        if (customer.getSalary().compareTo(BigDecimal.valueOf(25000)) <= 0) {
            logger.info(ResponseMessages.CARD_NOT_ISSUED, customer.getName());
            return;
        }

        CreditCardDetails card = new CreditCardDetails();
        card.setCustomer(customer);
        card.setCardNumber(generateCardNumber());
        card.setCvv(generateCVV());
        card.setExpiryMonth(LocalDate.now().getMonthValue());
        card.setExpiryYear(LocalDate.now().plusYears(5).getYear());
        card.setLimitAmount(ResponseMessages.FIXED_CREDIT_LIMIT);
        card.setCreatedAt(LocalDateTime.now());
        card.setLastUpdated(LocalDateTime.now());

        cardRepo.save(card);

        customer.setStatus(CustomerStatus.ACTIVE);
        customerRepo.save(customer);

        logger.info(ResponseMessages.CARD_ISSUED, customer.getName());
    }

    private String generateCardNumber() {
        long number = Math.abs(new Random().nextLong()) % 1_0000_0000_0000_0000L;
        return String.format("%016d", number);
    }

    private String generateCVV() {
        return String.format("%03d", new Random().nextInt(1000));
    }
}

