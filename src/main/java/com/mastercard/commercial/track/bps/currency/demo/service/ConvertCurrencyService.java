package com.mastercard.commercial.track.bps.currency.demo.service;

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

    public Payment updateReceivedPayment(Payment savedPayment) {
        responseMap = restTemplate.getForObject(currencyFixerURL + "?access_key=" + accessKey + "&from=" + savedPayment.getCurrencySent().toString()
                        + "&to=" + savedPayment.getCurrencyReceived().toString() + "&amount=" + Integer.toString(savedPayment.getTotalAmountSent()), new HashMap<>().getClass());

        resultAmount = responseMap.get("result").keySet().stream().findFirst().toString();
        totalAmountReceived = Double.parseDouble(resultAmount);
        savedPayment.setTotalAmountSent((int)totalAmountReceived);
        savedPayment.setStatus(PaymentStatus.APPROVED);
        paymentDbService.persistPayment(savedPayment);

        return savedPayment;
    }
}
