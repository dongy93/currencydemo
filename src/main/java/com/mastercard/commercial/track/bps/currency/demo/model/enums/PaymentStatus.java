package com.mastercard.commercial.track.bps.currency.demo.model.enums;

public enum PaymentStatus {

    RECEIVED("RECEIVED"),

    APPROVED("APPROVED"),

    DECLINED("DECLINED"),

    ERROR("ERROR");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static PaymentStatus fromValue(String text) {
        for(PaymentStatus b : PaymentStatus.values()) {
            if(String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
