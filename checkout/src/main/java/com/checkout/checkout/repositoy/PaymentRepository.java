package com.checkout.checkout.repositoy;

import com.checkout.checkout.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
    
}
