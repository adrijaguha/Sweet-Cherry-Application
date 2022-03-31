package com.capgemini.sweetcherry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.sweetcherry.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}

