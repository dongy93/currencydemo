package com.mastercard.commercial.track.bps.currency.demo.model;

import com.mastercard.commercial.track.bps.currency.demo.model.enums.BPSCurrency;
import com.mastercard.commercial.track.bps.currency.demo.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private String paymentId;
    private Integer totalAmountSent;
    private BPSCurrency currencySent;
    private BPSCurrency currencyReceived;
    private PaymentStatus status;
    private String errorDescription;
}
