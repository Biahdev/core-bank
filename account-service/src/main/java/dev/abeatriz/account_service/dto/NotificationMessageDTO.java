package dev.abeatriz.account_service.dto;

import dev.abeatriz.account_service.entity.NotificationChannel;

public record NotificationMessageDTO(
        Long accountId,
        NotificationChannel channel,
        String notes
) {
}
