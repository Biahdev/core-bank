package dev.abeatriz.transaction_service.dto;


import dev.abeatriz.transaction_service.entity.NotificationChannel;

import java.math.BigDecimal;

public record AccountDTO(
        Long accountId,
        String name,
        BigDecimal balance
) {
}
