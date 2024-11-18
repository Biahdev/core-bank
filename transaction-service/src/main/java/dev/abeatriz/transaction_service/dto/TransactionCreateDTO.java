package dev.abeatriz.transaction_service.dto;


import java.math.BigDecimal;

public record TransactionCreateDTO(
        Long sourceAccountId,
        Long destinationAccountId,
        String method,
        BigDecimal amount
) {
}
