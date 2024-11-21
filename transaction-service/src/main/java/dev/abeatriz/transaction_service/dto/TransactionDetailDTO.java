package dev.abeatriz.transaction_service.dto;


import dev.abeatriz.transaction_service.entity.NotificationChannel;
import dev.abeatriz.transaction_service.entity.TransactionMethod;
import dev.abeatriz.transaction_service.entity.TransactionStatus;

import java.math.BigDecimal;

public record TransactionDetailDTO(
        Long transactionId,
        Long sourceAccountId,
        Long destinationAccountId,
        BigDecimal amount,
        TransactionStatus status,
        TransactionMethod method
) {
}
