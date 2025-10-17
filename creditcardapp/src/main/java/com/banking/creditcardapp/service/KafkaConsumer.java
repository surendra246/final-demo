package com.banking.creditcardapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.banking.creditcardapp.dto.CreditCardApplicationKafkaMessage;
import com.banking.creditcardapp.entity.ApplicationRequest;
import com.banking.creditcardapp.repository.ApplicationRequestRepository;
import com.banking.creditcardapp.repository.CreditCardDetailsRepository;

@Component
public class KafkaConsumer {

    @Autowired
    private ApplicationRequestRepository applicationRepo;

    @Autowired
    private CreditCardDetailsRepository cardRepo;

@KafkaListener(topics = "creditcard_application", groupId = "creditcard-group")
public void consume(CreditCardApplicationKafkaMessage message) {
    Optional<ApplicationRequest> optionalRequest = applicationRepo.findById(message.getApplicationId());
   System.out.println("Response == " + optionalRequest);

    // if (optionalRequest.isPresent()) {
    //     ApplicationRequest request = optionalRequest.get();
    //     request.setStatus(ApplicationStatus.valueOf(message.getStatus().toUpperCase()));
    //     request.setRemarks(message.getRemarks());
    //     applicationRepo.save(request);

    //     if ("APPROVED".equalsIgnoreCase(message.getStatus())) {
    //         Customer customer = request.getCustomer();

    //         if (customer.getSalary().compareTo(BigDecimal.valueOf(25000)) > 0) {
    //             CreditCardDetails card = new CreditCardDetails();
    //             card.setCustomer(customer);
    //             card.setCardNumber(generateCardNumber());
    //             card.setCvv(generateCVV());
    //             card.setExpiryMonth(LocalDate.now().getMonthValue());
    //             card.setExpiryYear(LocalDate.now().plusYears(5).getYear());
    //             card.setLimitAmount(customer.getSalary().multiply(BigDecimal.valueOf(2)));
    //             card.setCreatedAt(LocalDateTime.now());
    //             card.setLastUpdated(LocalDateTime.now());

    //             cardRepo.save(card);
    //             System.out.println("Credit card issued for customer: " + customer.getName());
    //         } else {
    //             System.out.println("Credit card not issued for " + customer.getName() + ": Salary below threshold.");
    //         }
    //     }
    // } else {
    //     System.out.println("Application not found for ID: " + message.getApplicationId());
    // }
}


    // private String generateCardNumber() {
    //     return String.format("%016d", new Random().nextLong() & Long.MAX_VALUE);
    // }

    // private String generateCVV() {
    //     return String.format("%03d", new Random().nextInt(1000));
    // }
}



