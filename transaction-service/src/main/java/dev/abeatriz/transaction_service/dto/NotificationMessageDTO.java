package dev.abeatriz.transaction_service.dto;


import dev.abeatriz.transaction_service.entity.NotificationChannel;

public record NotificationMessageDTO(
        Long accountId,
        NotificationChannel channel,
        String notes
) {
}
