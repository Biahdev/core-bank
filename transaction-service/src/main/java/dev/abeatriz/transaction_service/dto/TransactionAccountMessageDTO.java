package dev.abeatriz.transaction_service.dto;

import java.math.BigDecimal;

public record TransactionAccountMessageDTO(
        Long sourceAccountId,
        Long destinationAccountId,
        Long transactionId,
        BigDecimal amount
) {
}
