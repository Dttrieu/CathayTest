package com.example.demo.controller;

import com.example.demo.model.dto.ExchangeRatesResponseDTO;
import com.example.demo.service.impl.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRatesController {

    @Autowired
    ExchangeRatesService exchangeRatesService;

    @GetMapping
    public ExchangeRatesResponseDTO getExchangeRates(@RequestParam String base,
                                                     @RequestParam(name = "start_date") String startDate,
                                                     @RequestParam(name = "end_date") String endDate){

        return exchangeRatesService.getExchangeRates(base, startDate, endDate);
    }
}
