package com.mastercard.commercial.track.bps.currency.demo.service;


import com.mastercard.commercial.track.bps.currency.demo.model.*;
import com.mastercard.commercial.track.bps.currency.demo.model.enums.PaymentStatus;
import com.mastercard.commercial.track.bps.currency.demo.persistence.entity.Payment;
import com.mastercard.commercial.track.bps.currency.demo.service.ConvertCurrencyService;
import com.mastercard.commercial.track.bps.currency.demo.service.PaymentDbService;
import com.mastercard.commercial.track.bps.currency.demo.util.PaymentUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class CreatePaymentService {
    private final ModelMapper modelMapper;
    private final ConvertCurrencyService convertCurrencyService;
    private final PaymentDbService paymentDbService;

    @Autowired
    public CreatePaymentService(ModelMapper modelMapper, ConvertCurrencyService convertCurrencyService, PaymentDbService paymentDbService) {
        this.modelMapper = modelMapper;
        this.convertCurrencyService = convertCurrencyService;
        this.paymentDbService = paymentDbService;
    }

    @Transactional
    public PaymentResponseDTO execute(PaymentRequestDTO paymentRequestDTO) {
        Payment payment = modelMapper.map(paymentRequestDTO, Payment.class);
        Payment savedPayment = paymentDbService.persistPayment(payment.preparePaymentForInsertUpdate(PaymentStatus.RECEIVED));
        savedPayment = convertCurrencyService.updatePayment(savedPayment);
        return PaymentUtil.getPaymentResponse(savedPayment);
    }
}
