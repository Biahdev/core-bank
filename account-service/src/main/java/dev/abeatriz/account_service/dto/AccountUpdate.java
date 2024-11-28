package dev.abeatriz.account_service.dto;

import dev.abeatriz.account_service.entity.AccountStatus;
import dev.abeatriz.account_service.entity.AccountType;
import dev.abeatriz.account_service.exception.enums.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(title = "Atualização de Conta", description = "Objeto para atualização das informações da conta bancária.")
public record AccountUpdate(
        @NotBlank
        @Size(min = 3, max = 150)
        @Schema(title = "Nome Completo do Titular", example = "Ana Beatriz", type = "string", minLength = 3, maxLength = 150)
        String name,

        @NotBlank
        @Size(min = 3, max = 100)
                @Schema(title = "Documento de Identificação", example = "123.231.123-23", type = "string", minLength = 3, maxLength = 50)
        String document,

        @ValueOfEnum(enumClass = AccountStatus.class)
        @Schema(title = "Status da conta", example = "ATIVO", type = "string", defaultValue = "ATIVO", allowableValues = {"ATIVO", "INATIVO"})
        AccountStatus status,

        @ValueOfEnum(enumClass = AccountType.class)
        @Schema(title = "Tipo da conta", example = "CORRENTE", type = "string", defaultValue = "CORRENTE", allowableValues = {"CORRENTE", "POUPANCA"})
        AccountType type
) {
}
