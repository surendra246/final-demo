package com.banking.creditcardapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.creditcardapp.dto.CreditCardApplicationRequest;
import com.banking.creditcardapp.dto.CreditCardApplicationResponse;
import com.banking.creditcardapp.service.CreditCardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/creditcard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/apply")
    public ResponseEntity<CreditCardApplicationResponse> apply(@Valid @RequestBody CreditCardApplicationRequest request) {
        return ResponseEntity.ok(creditCardService.applyForCreditCard(request));
    }


    @GetMapping("/")
    public String welcome() {
        return "Credit Card API is running!";
    }
}
