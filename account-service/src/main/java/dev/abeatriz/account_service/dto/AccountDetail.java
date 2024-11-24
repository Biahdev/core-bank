package dev.abeatriz.account_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.abeatriz.account_service.entity.AccountStatus;
import dev.abeatriz.account_service.entity.AccountType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDetail(
        Long accountId,
        String name,
        String document,
        BigDecimal balance,
        AccountStatus status,
        AccountType type,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime updatedAt
) {
}
