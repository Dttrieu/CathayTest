package com.example.demo.service;

import com.example.demo.service.impl.ExchangeRatesService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class ExchangeRatesTest {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void testGetExchangeRatesWhenBaseCurrencyIsNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            exchangeRatesService.getExchangeRates(null, "2025-10-28", "2025-10-30");
        });

        assertEquals("baseCurrency is null", exception.getMessage());
    }

    @Test
    void testGetExchangeRatesWhenStartDateIsNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            exchangeRatesService.getExchangeRates("VND", null, "2025-10-30");
        });

        assertEquals("startDate is null", exception.getMessage());
    }

    @Test
    void testGetExchangeRatesWhenEndDateIsNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            exchangeRatesService.getExchangeRates("VND", "2025-10-28", null);
        });

        assertEquals("endDate is null", exception.getMessage());
    }

    @Test
    void testGetExchangeRatesWhenDateIsNotCorrectFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            exchangeRatesService.getExchangeRates("VND", "2025/10/28", "2025-10-30");
        });

        assertEquals("Start Date or End Date is not correct format", exception.getMessage());
    }

    @Test
    void testGetExchangeRatesWhenStartDateLessThanEndDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            exchangeRatesService.getExchangeRates("VND", "2025-10-30", "2025-10-28");
        });

        assertEquals("Start date must be less than End date", exception.getMessage());
    }

}
