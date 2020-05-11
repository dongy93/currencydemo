package com.mastercard.commercial.track.bps.currency.demo.persistence.entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class PaymentIdGenerator implements IdentifierGenerator {

    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return formatWithDash("T", Long.toHexString(System.nanoTime()).toUpperCase() + RandomStringUtils.randomAlphanumeric(7).toUpperCase(), 3);
    }

    private String formatWithDash(String prefix, String input, int position) {
        return String.format("%s-%s", prefix, input.replaceAll("(.{"+ position + "})(?!$)", "$1-"));
    }
}
