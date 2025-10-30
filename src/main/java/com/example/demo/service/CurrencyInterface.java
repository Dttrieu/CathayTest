package com.example.demo.service;

import com.example.demo.model.dto.ExchangeRatesResponseDTO;
import com.example.demo.model.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyInterface<T> {

    List<Currency> getAllCurrencies();
    Optional<Currency> getCurrencyByCode(String code);
    Currency addCurrency(Currency currency);
    Currency updateCurrency(Currency currency);
    void deleteCurrency(String code);
}