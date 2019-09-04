package com.mindtree.letswork.module.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.letswork.module.booking.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
