package com.example.demo.service.impl;

import com.example.demo.model.dto.ExchangeRatesDTO;
import com.example.demo.model.dto.ExchangeRatesResponseDTO;
import com.example.demo.service.ExchangeRatesInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class ExchangeRatesService implements ExchangeRatesInterface {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ExchangeRatesResponseDTO getExchangeRates(String baseCurrency, String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDate start_date;
        LocalDate end_date;

        //validate input
        if (baseCurrency == null) throw new NullPointerException("baseCurrency is null");
        if (startDate == null) throw new NullPointerException("startDate is null");
        if (endDate == null) throw new NullPointerException("endDate is null");

        try {
            start_date = LocalDate.parse(startDate);
            end_date = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Start Date or End Date is not correct format");
        }

        if (!start_date.isBefore(end_date)) {
            throw new IllegalArgumentException("Start date must be less than End date");
        }

        // use UriComponentsBuilder to build url
        String url = UriComponentsBuilder
                .fromUri(URI.create("https://fxds-public-exchange-rates-api.oanda.com/cc-api/currencies"))
                .queryParam("base", baseCurrency)
                .queryParam("quote", "USD")
                .queryParam("data_type", "chart")
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .toUriString();

        System.out.println("URL: " + url);

        // call api and then parse into ExchangeRatesResponseDTO
        ExchangeRatesResponseDTO exchangeRatesResponseDTO = restTemplate.getForObject(url, ExchangeRatesResponseDTO.class);

        if (exchangeRatesResponseDTO == null || exchangeRatesResponseDTO.getResponse() == null) {
            throw new RuntimeException("Response is null");
        }

        List<ExchangeRatesDTO> ExchangeRatesList = exchangeRatesResponseDTO.getResponse();

        for (ExchangeRatesDTO rate : ExchangeRatesList) {
//            rate.setCloseTime(rate.getCloseTime().format(String.valueOf(formatter)));
            rate.setUpdateTime(LocalDateTime.now().format(formatter));
        }

        return exchangeRatesResponseDTO;
    }

}
