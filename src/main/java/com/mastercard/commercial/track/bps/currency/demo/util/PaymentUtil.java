package com.mastercard.commercial.track.bps.currency.demo.util;

import com.mastercard.commercial.track.bps.currency.demo.model.PaymentResponseDTO;
import com.mastercard.commercial.track.bps.currency.demo.persistence.entity.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentUtil {

    private PaymentUtil() {
    }

    public static PaymentResponseDTO getPaymentResponse(Payment savedPayment) {
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setPaymentId(savedPayment.getPaymentId());
        paymentResponseDTO.setDescription(savedPayment.getPaymentId());
        paymentResponseDTO.setStatus(savedPayment.getStatus());
        return paymentResponseDTO;
    }
}
