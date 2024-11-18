package dev.abeatriz.transaction_service.dto;


import dev.abeatriz.transaction_service.entity.NotificationChannel;

public record TransactionDetailDTO(
        Long accountId,
        NotificationChannel channel,
        String notes
) {
}
