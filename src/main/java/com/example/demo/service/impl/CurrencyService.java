package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.Currency;
import com.example.demo.model.repository.CurrencyRepository;
import com.example.demo.service.CurrencyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService implements CurrencyInterface {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAllByOrderByCodeAsc();

        if (currencies.isEmpty()) {
            throw new ResourceNotFoundException("Data not found");
        }

        return currencies;
    }

    @Override
    public Optional<Currency> getCurrencyByCode(String code) {
        return Optional.ofNullable(currencyRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Code " + code + " is not exist")));
    }

    @Override
    public Currency addCurrency(Currency currency) {
        String code = currency.getCode();

        //check this code is exist or not
        // if exist -> throw error
        // else insert db
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code must be not null");
        }

        if (currencyRepository.existsById(code)) {
            throw new IllegalArgumentException("Code " + code + " exists");
        }

        return currencyRepository.saveAndFlush(currency);
    }

    @Override
    public Currency updateCurrency(Currency currency) {
        String code = currency.getCode();

        //check this code is exist or not
        // if not exist -> throw error
        // else update db
        Currency isExist = currencyRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Code " + code + " is not exist"));

        // update data
        isExist.setName(currency.getName());
        isExist.setCountry(currency.getCountry());
        isExist.setUpdatedUser(currency.getUpdatedUser());

        return currencyRepository.save(isExist);
    }

    @Override
    public void deleteCurrency(String code) {
        //check this code is exist or not
        // if not exist -> throw error
        // else update db
        Currency isExist = currencyRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Code " + code + " is not exist"));

        currencyRepository.deleteById(code);
    }
}

