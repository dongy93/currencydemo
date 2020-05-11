package com.mastercard.commercial.track.bps.currency.demo.mapping;

import com.mastercard.commercial.track.bps.currency.demo.model.PaymentDTO;
import com.mastercard.commercial.track.bps.currency.demo.persistence.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentToPaymentDTOMapping extends PropertyMap<Payment, PaymentDTO> {

    @Override
    protected void configure() {
        map().setPaymentId(source.getPaymentId());
        map().setCurrencySent(source.getCurrencySent());
        map().setCurrencyReceived(source.getCurrencyReceived());
        map().setErrorDescription(source.getErrorDescription());
        map().setStatus(source.getStatus());
        map().setTotalAmountReceived(source.getTotalAmountReceived());
        map().setTotalAmountSent(source.getTotalAmountSent());
    }
}
