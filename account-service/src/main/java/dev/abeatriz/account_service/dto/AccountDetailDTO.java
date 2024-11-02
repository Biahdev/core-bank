package dev.abeatriz.account_service.dto;

import dev.abeatriz.account_service.entity.AccountStatus;
import dev.abeatriz.account_service.entity.AccountType;

import java.math.BigDecimal;

public record AccountDetailDTO(
        Long accountId,

        String name,

        String document,

        BigDecimal balance,

        AccountStatus status,

        AccountType type
        ) {
}
