package com.mastercard.commercial.track.bps.currency.demo.service;

import com.mastercard.commercial.track.bps.currency.demo.model.enums.BPSCurrency;
import com.mastercard.commercial.track.bps.currency.demo.model.enums.PaymentStatus;
import com.mastercard.commercial.track.bps.currency.demo.persistence.entity.Payment;
import com.mastercard.commercial.track.bps.currency.demo.service.PaymentDbService;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.HashMap;

@Service
@Slf4j
public class ConvertCurrencyService {

    private PaymentDbService paymentDbService;
    private RestTemplate restTemplate;

    @Value("${currencyfixer.accessKey}")
    private String accessKey;

    @Value("${currencyfixer.url")
    private String currencyFixerURL;

    private HashMap<String,Map> responseMap;
    private String resultAmount;
    private double totalAmountReceived;

    @Autowired
    public ConvertCurrencyService(PaymentDbService paymentDbService, RestTemplate restTemplate) {
        this.paymentDbService = paymentDbService;
        this.restTemplate = restTemplate;
    }

    public int convertReceivedPayment(int totalAmountSent, BPSCurrency currencySent, BPSCurrency currencyReceived) {
        responseMap = restTemplate.getForObject(currencyFixerURL + "?access_key=" + accessKey + "&from=" + currencySent.getName()
                        + "&to=" + currencyReceived.getName() + "&amount=" + Integer.toString(totalAmountSent), new HashMap<>().getClass());

        resultAmount = responseMap.get("result").keySet().stream().findFirst().toString();
        totalAmountReceived = Double.parseDouble(resultAmount);
        return (int) totalAmountReceived;
    }

    public Payment updatePayment(Payment savedPayment) {
        savedPayment.setTotalAmountReceived(convertReceivedPayment(savedPayment.getTotalAmountSent(), savedPayment.getCurrencySent(), savedPayment.getCurrencyReceived()));
        savedPayment.setStatus(PaymentStatus.APPROVED);
        paymentDbService.persistPayment(savedPayment);

        return savedPayment;
    }
}
