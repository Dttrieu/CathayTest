package com.example.demo.service;

import com.example.demo.scheduler.ExchangeRatesScheduler;
import com.example.demo.service.impl.ExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExchangeRatesSchedulerTest {

    @Mock
    private ExchangeRatesService exchangeRateService;

    @InjectMocks
    private ExchangeRatesScheduler scheduler;

    @Test
    void testSyncExchangeRates() {
        scheduler.syncExchangeRates();

        verify(exchangeRateService, times(1))
                .getExchangeRates(anyString(), anyString(), anyString());
    }
}