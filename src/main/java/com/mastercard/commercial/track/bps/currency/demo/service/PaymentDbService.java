package com.mastercard.commercial.track.bps.currency.demo.service;


import com.mastercard.commercial.track.bps.currency.demo.persistence.entity.Payment;
import com.mastercard.commercial.track.bps.currency.demo.persistence.repository.CardPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDbService {
    private final CardPaymentRepository cardPaymentRepository;

    @Autowired
    public PaymentDbService(CardPaymentRepository cardPaymentRepository) {
        this.cardPaymentRepository = cardPaymentRepository;
    }

    public Payment persistPayment(Payment payment) {
        return cardPaymentRepository.save(payment);
    }
}
