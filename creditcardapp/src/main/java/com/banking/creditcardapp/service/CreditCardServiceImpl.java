package com.banking.creditcardapp.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.banking.creditcardapp.dto.CreditCardApplicationKafkaMessage;
import com.banking.creditcardapp.dto.CreditCardApplicationRequest;
import com.banking.creditcardapp.dto.CreditCardApplicationResponse;
import com.banking.creditcardapp.entity.ApplicationRequest;
import com.banking.creditcardapp.entity.ApplicationStatus;
import com.banking.creditcardapp.entity.Customer;
import com.banking.creditcardapp.entity.CustomerStatus;
import com.banking.creditcardapp.repository.ApplicationRequestRepository;
import com.banking.creditcardapp.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CustomerRepository customerRepository;
    private final ApplicationRequestRepository applicationRequestRepository;
    private final KafkaProducer kafkaProducer;
    private final ModelMapper modelMapper;

    @Override
    public CreditCardApplicationResponse applyForCreditCard(CreditCardApplicationRequest request) {
        // Map DTO to Entity
        Customer customer = modelMapper.map(request, Customer.class);
        customer.setStatus(CustomerStatus.PENDING);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setLastUpdated(LocalDateTime.now());

        Customer savedCustomer = customerRepository.save(customer);

        ApplicationRequest application = ApplicationRequest.builder()
                .customer(savedCustomer)
                .status(ApplicationStatus.PENDING)
                .remarks("Application submitted")
                .applicationDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        ApplicationRequest savedApplication = applicationRequestRepository.save(application);

        // Send Kafka message
        CreditCardApplicationKafkaMessage kafkaMessage = CreditCardApplicationKafkaMessage.builder()
                .applicationId(savedApplication.getId())
                .status("PENDING")
                .remarks("Application submitted")
                .build();

        kafkaProducer.sendApplicationMessage(kafkaMessage);

        // Map Entity to Response DTO
        return modelMapper.map(savedApplication, CreditCardApplicationResponse.class);
    }
}
