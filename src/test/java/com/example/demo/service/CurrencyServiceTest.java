package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.entity.Currency;
import com.example.demo.model.repository.CurrencyRepository;
import com.example.demo.service.impl.CurrencyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    void testGetCurrencyByCodeWhenSuccess() {
        Currency currency = new Currency();
        currency.setCode("VND");
        currency.setName("Viet Nam Dong");
        currency.setCountry("Viet Nam");
        currency.setCreatedUser("Tester");
        currencyService.addCurrency(currency);

        Optional<Currency> found = currencyService.getCurrencyByCode("VND");

        assertTrue(found.isPresent());
        assertEquals("VND", found.get().getCode());
        assertEquals("Viet Nam Dong", found.get().getName());
    }

    @Test
    void testGetAllCurrencies() {
        Currency vnd = new Currency();
        vnd.setCode("VND");
        vnd.setName("Viet Nam Dong");
        vnd.setCountry("Viet Nam");
        vnd.setCreatedUser("Tester");

        Currency jpy = new Currency();
        jpy.setCode("JPY");
        jpy.setName("Yen");
        jpy.setCountry("Japan");
        jpy.setCreatedUser("Tester");

        Currency usd = new Currency();
        usd.setCode("USD");
        usd.setName("US Dollar");
        usd.setCountry("USA");
        usd.setCreatedUser("Tester");

        currencyService.addCurrency(vnd);
        currencyService.addCurrency(jpy);
        currencyService.addCurrency(usd);

        // When
        List<Currency> result = currencyService.getAllCurrencies();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("JPY", result.get(0).getCode());
    }

    @Test
    void testGetCurrencyByCodeWhenCodeIsNotExist() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            currencyService.getCurrencyByCode("EUR");
        });

        assertEquals("Code EUR is not exist", exception.getMessage());
    }

    @Test
    void testAddCurrencyWhenCodeIsNull() {
        Currency currency = new Currency();
        currency.setCode("");
        currency.setName("Viet Nam Dong");
        currency.setCountry("Viet Nam");
        currency.setCreatedUser("Tester");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            currencyService.addCurrency(currency);
        });

        assertEquals("Code must be not null", exception.getMessage());
    }

    @Test
    void testAddCurrencyWhenCodeIsExist() {
        Currency currency = new Currency();
        currency.setCode("VND");
        currency.setName("Viet Nam Dong");
        currency.setCountry("Viet Nam");
        currency.setCreatedUser("Tester");

        // add first time -> OK
        Currency vnd = currencyService.addCurrency(currency);

        // add second time -> error
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            currencyService.addCurrency(currency);
        });
        assertEquals("Code " + currency.getCode() + " exists" , exception.getMessage());
    }

    @Test
    void testAddCurrencySuccess() {
        Currency currency = new Currency();
        currency.setCode("VND");
        currency.setName("Viet Nam Dong");
        currency.setCountry("Viet Nam");
        currency.setCreatedUser("Tester");

        Currency vnd = currencyService.addCurrency(currency);

        assertNotNull(vnd);
        assertEquals("VND", vnd.getCode());
        assertEquals("Viet Nam Dong", vnd.getName());
        assertEquals("Viet Nam", vnd.getCountry());
        assertNotNull(vnd.getCreatedAt());
    }

    @Test
    void testUpdateCurrencyWhenCodeIsNotExist() {
        Currency updatedCurrency = new Currency();
        updatedCurrency.setCode("VND");
        updatedCurrency.setName("Vietnamese Dong");
        updatedCurrency.setCountry("VN");
        updatedCurrency.setUpdatedUser("Updater");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            currencyService.updateCurrency(updatedCurrency);
        });
        assertEquals("Code " + updatedCurrency.getCode() + " is not exist" , exception.getMessage());
    }

    @Test
    void testUpdateCurrencySuccess() {
        //add
        Currency currency = new Currency();
        currency.setCode("VND");
        currency.setName("Viet Nam Dong");
        currency.setCountry("Viet Nam");
        currency.setCreatedUser("Tester");
        currencyService.addCurrency(currency);

        //update
        Currency updatedCurrency = new Currency();
        updatedCurrency.setCode("VND");
        updatedCurrency.setName("Vietnamese Dong");
        updatedCurrency.setCountry("VN");
        updatedCurrency.setUpdatedUser("Updater");

        Currency result = currencyService.updateCurrency(updatedCurrency);

        assertNotNull(result);
        assertEquals("VND", result.getCode());
        assertEquals("Vietnamese Dong", result.getName());
        assertEquals("VN", result.getCountry());
        assertEquals("Updater", result.getUpdatedUser());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    void testDeleteCurrencyWhenCodeIsNotExist() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            currencyService.deleteCurrency("VND");
        });
        assertEquals("Code VND is not exist" , exception.getMessage());
    }

    @Test
    void testDeleteCurrencySuccess() {
        //add
        Currency currency = new Currency();
        currency.setCode("VND");
        currency.setName("Viet Nam Dong");
        currency.setCountry("Viet Nam");
        currency.setCreatedUser("Tester");
        currencyService.addCurrency(currency);

        //delete
        currencyService.deleteCurrency("VND");

        assertFalse(currencyRepository.existsById("VND"));
    }
}
