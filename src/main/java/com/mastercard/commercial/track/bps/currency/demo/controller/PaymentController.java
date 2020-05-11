package com.mastercard.commercial.track.bps.currency.demo.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.commercial.track.bps.currency.demo.model.*;
import com.mastercard.commercial.track.bps.currency.demo.service.CreatePaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@Slf4j
public class PaymentController {
    private final CreatePaymentService createPaymentService;
    private final ObjectMapper objectMapper;

    public PaymentController(CreatePaymentService createPaymentService, ObjectMapper objectMapper) {
        this.createPaymentService = createPaymentService;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        this.objectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
    }

    @PostMapping(value = "/payments",
        produces = {"application/json"},
        consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponseDTO paymentPost(@Valid @RequestBody PaymentRequestDTO paymentDTO) {
        return createPaymentService.execute(paymentDTO);
    }



}
