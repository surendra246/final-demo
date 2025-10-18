package com.banking.creditcardapp.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.banking.creditcardapp.constants.ResponseMessages;
import com.banking.creditcardapp.dto.CreditCardApplicationKafkaMessage;
import com.banking.creditcardapp.dto.CreditCardApplicationRequest;
import com.banking.creditcardapp.dto.CreditCardApplicationResponse;
import com.banking.creditcardapp.entity.ApplicationRequest;
import com.banking.creditcardapp.entity.Customer;
import com.banking.creditcardapp.entity.CustomerStatus;
import com.banking.creditcardapp.enums.ApplicationStatus;
import com.banking.creditcardapp.exception.DuplicateCustomerException;
import com.banking.creditcardapp.repository.ApplicationRequestRepository;
import com.banking.creditcardapp.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardService.class);
    private final CustomerRepository customerRepository;
    private final ApplicationRequestRepository applicationRequestRepository;
    private final CreditCardApplicationProducer creditCardApplicationProducer;
    private final ModelMapper modelMapper;

    @Override
    public CreditCardApplicationResponse applyForCreditCard(CreditCardApplicationRequest request) {

        // Normalize input
        String email = request.getEmail().trim().toLowerCase();
        String phone = request.getPhone().trim();

        logger.info(ResponseMessages.CHECKING_EMAIL, email);
        if (customerRepository.existsByEmail(email)) {
            logger.warn(ResponseMessages.DUPLICATE_EMAIL_LOG, email);
            throw new DuplicateCustomerException(ResponseMessages.DUPLICATE_EMAIL + ": " + email);
        }

        logger.info(ResponseMessages.CHECKING_PHONE, phone);
        if (customerRepository.existsByPhone(phone)) {
            logger.warn(ResponseMessages.DUPLICATE_PHONE_LOG, phone);
            throw new DuplicateCustomerException(ResponseMessages.DUPLICATE_PHONE + ": " + phone);
        }
        // Map and persist customer
        Customer customer = mapToCustomer(request, email, phone);
        Customer savedCustomer = customerRepository.save(customer);

        // Create and persist application
        ApplicationRequest application = ApplicationRequest.builder()
                .customer(savedCustomer)
                .status(ApplicationStatus.PENDING)
                .remarks("Application submitted")
                .applicationDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        ApplicationRequest savedApplication = applicationRequestRepository.save(application);

        // Send Kafka message
        creditCardApplicationProducer.sendApplicationMessage(
                CreditCardApplicationKafkaMessage.builder()
                        .applicationId(savedApplication.getId())
                        .status("PENDING")
                        .remarks("Application submitted")
                        .build()
        );

        // Map response
        CreditCardApplicationResponse response = modelMapper.map(savedApplication, CreditCardApplicationResponse.class);
        response.setMessage(ResponseMessages.APPLICATION_SUBMITTED);

        return response;
    }

    private Customer mapToCustomer(CreditCardApplicationRequest request, String email, String phone) {
        Customer customer = modelMapper.map(request, Customer.class);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setStatus(CustomerStatus.PENDING);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setLastUpdated(LocalDateTime.now());
        return customer;
    }
}
