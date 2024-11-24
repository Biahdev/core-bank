package dev.abeatriz.account_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AccountCreate(
        @NotBlank
        @Size(min = 3, max = 150)
        String name,

        @NotBlank
        @Size(min = 3, max = 100)
        String document,

        BigDecimal amount
) {
}
