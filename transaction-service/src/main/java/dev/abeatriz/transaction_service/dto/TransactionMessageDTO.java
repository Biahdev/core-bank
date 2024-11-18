package dev.abeatriz.transaction_service.dto;

import dev.abeatriz.transaction_service.entity.TransactionMethod;

import java.math.BigDecimal;

public record TransactionMessageDTO(
        Long accountId,
        Long transactionId,
        BigDecimal amount,
        TransactionMethod method
) {
}
