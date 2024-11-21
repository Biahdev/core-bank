package dev.abeatriz.account_service.dto;

import java.math.BigDecimal;

public record TransactionAccountMessage(
        Long sourceAccountId,
        Long destinationAccountId,
        Long transactionId,
        BigDecimal amount
) {
}
