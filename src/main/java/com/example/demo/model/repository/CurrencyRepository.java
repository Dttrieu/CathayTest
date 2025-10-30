package com.example.demo.model.repository;

import com.example.demo.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    List<Currency> findAllByOrderByCodeAsc();

    Optional<Currency> findByCode(String code);

    void deleteByCode(String code);
}
