package com.mastercard.commercial.track.bps.currency.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mastercard.commercial.track.bps.currency.demo.model.enums.PaymentStatus;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponseDTO {
    private String paymentId;
    private PaymentStatus status;
    private String description;
}
