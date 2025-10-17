package com.banking.creditcardapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.creditcardapp.entity.CreditCardDetails;

public interface CreditCardDetailsRepository extends JpaRepository<CreditCardDetails, Integer> {
    Optional<CreditCardDetails> findByCustomerEmail(String email);
}
