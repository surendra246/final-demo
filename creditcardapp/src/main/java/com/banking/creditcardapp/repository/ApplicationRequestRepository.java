package com.banking.creditcardapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.creditcardapp.entity.ApplicationRequest;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {}
