package com.banking.creditcardapp.service;

import com.banking.creditcardapp.dto.CreditCardApplicationRequest;
import com.banking.creditcardapp.dto.CreditCardApplicationResponse;

public interface CreditCardService {

    /**
     * Applies for a credit card using customer details.
     *
     * @param request the credit card application request DTO
     * @return the application response DTO
     */
    CreditCardApplicationResponse applyForCreditCard(CreditCardApplicationRequest request);
}
