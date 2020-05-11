package com.mastercard.commercial.track.bps.currency.demo.persistence.entity;

import com.mastercard.commercial.track.bps.currency.demo.model.enums.PaymentStatus;
import com.mastercard.commercial.track.bps.currency.demo.model.enums.BPSCurrency;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
public class Payment {

    @Id
    @GenericGenerator(name = "sequence_payment_id", strategy = "com.mastercard.commercial.track.bps.currency.demo.persistence.entity.PaymentIdGenerator")
    @GeneratedValue(generator = "sequence_payment_id")
    private String paymentId;
    private Integer totalAmountSent;
    private Integer totalAmountReceived;
    private String errorDescription;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BPSCurrency currencySent;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BPSCurrency currencyReceived;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Transient
    public Payment preparePaymentForInsertUpdate(PaymentStatus status) {
        this.status = status;
        return this;
    }
}
