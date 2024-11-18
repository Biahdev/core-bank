package dev.abeatriz.account_service.dto;

import dev.abeatriz.account_service.entity.TransactionType;

import java.math.BigDecimal;

public record TransactionMessageDTO(
        Long accountId,
        Long transactionId,
        BigDecimal amount,
        TransactionType type
) {
}
