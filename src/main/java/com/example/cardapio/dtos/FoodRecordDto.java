package com.example.cardapio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FoodRecordDto(@NotBlank String title, @NotBlank String image, @NotNull BigDecimal price) {
}
