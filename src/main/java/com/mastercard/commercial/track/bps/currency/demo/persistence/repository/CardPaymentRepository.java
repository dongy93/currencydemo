package com.mastercard.commercial.track.bps.currency.demo.persistence.repository;

import com.mastercard.commercial.track.bps.currency.demo.persistence.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CardPaymentRepository extends JpaRepository<Payment, String>, QuerydslPredicateExecutor<Payment> {
}
