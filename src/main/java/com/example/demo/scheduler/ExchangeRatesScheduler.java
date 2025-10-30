package com.example.demo.scheduler;

import com.example.demo.model.dto.ExchangeRatesResponseDTO;
import com.example.demo.service.impl.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ExchangeRatesScheduler {

    @Autowired
    private ExchangeRatesService exchangeRateService;

    @Scheduled(cron = "0 0 8 * * *")
    public void syncExchangeRates() {
        System.out.println("********** Start schedule synchronization of exchange rates at" + LocalDateTime.now() + " **********");

        try {
            String base = "VND";
            String startDate = LocalDate.now().minusDays(1).toString();
            String endDate = LocalDate.now().toString();

            // Gọi API lấy tỷ giá
            ExchangeRatesResponseDTO response = exchangeRateService.getExchangeRates(base, startDate, endDate);

            System.out.println("Start schedule synchronization successly at " + LocalDateTime.now());
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }

        System.out.println("********** End schedule synchronization of exchange rates at **********");
    }
}
