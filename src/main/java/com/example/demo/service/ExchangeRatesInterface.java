package com.example.demo.service;

import com.example.demo.model.dto.ExchangeRatesResponseDTO;

public interface ExchangeRatesInterface<T> {

    ExchangeRatesResponseDTO getExchangeRates(String baseCurrency, String startDate, String endDate);

}
