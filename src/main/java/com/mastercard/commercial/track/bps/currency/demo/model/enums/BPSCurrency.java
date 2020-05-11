package com.mastercard.commercial.track.bps.currency.demo.model.enums;

public enum BPSCurrency {
    USD("USD"),
    INR("INR"),
    KRW("KRW"),
    EUR("EUR");

    private String name;

    private BPSCurrency(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
