package com.banking.creditcardapp.mapper;



import java.time.LocalDate;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.creditcardapp.dto.CreditCardApplicationRequest;
import com.banking.creditcardapp.dto.CreditCardApplicationResponse;
import com.banking.creditcardapp.dto.CreditCardSearchResponse;
import com.banking.creditcardapp.entity.ApplicationRequest;
import com.banking.creditcardapp.entity.CreditCardDetails;
import com.banking.creditcardapp.entity.Customer;
import com.banking.creditcardapp.entity.CustomerStatus;

@Component
public class CreditCardMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Customer toCustomer(CreditCardApplicationRequest request) {
        Customer customer = modelMapper.map(request, Customer.class);
        customer.setStatus(CustomerStatus.PENDING);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setLastUpdated(LocalDateTime.now());
        return customer;
    }

    public CreditCardApplicationResponse toApplicationResponse(ApplicationRequest app) {
        return modelMapper.map(app, CreditCardApplicationResponse.class);
    }

    public CreditCardSearchResponse toSearchResponse(CreditCardDetails card) {
        CreditCardSearchResponse response = modelMapper.map(card, CreditCardSearchResponse.class);
        response.setCustomerName(card.getCustomer().getName());
        response.setExpiryDate(LocalDate.of(card.getExpiryYear(), card.getExpiryMonth(), 1));
        return response;
    }
}

