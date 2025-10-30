package com.example.demo.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class ExchangeRatesResponseDTO {
    private List<ExchangeRatesDTO> response;
}
