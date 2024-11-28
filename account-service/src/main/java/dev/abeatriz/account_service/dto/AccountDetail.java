package dev.abeatriz.account_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.abeatriz.account_service.entity.AccountStatus;
import dev.abeatriz.account_service.entity.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(title = "Dados Detalhados da Conta Bancária", description = "Detalhes completos de uma conta bancária")
public record AccountDetail(
        @Schema(title = "Identificador da Conta", example = "1", type = "long", minimum = "1")
        Long accountId,

        @Schema(title = "Nome Completo do Titular", example = "Ana Beatriz", type = "string", minLength = 3, maxLength = 150)
        String name,

        @Schema(title = "Documento de Identificação", example = "123.231.123-23", type = "string", minLength = 3, maxLength = 50)
        String document,

        @Schema(title = "Saldo bancário", example = "100.00", type = "double")
        BigDecimal balance,

        @Schema(title = "Status da conta", example = "ATIVO", type = "string", defaultValue = "ATIVO", allowableValues = {"ATIVO", "INATIVO"})
        AccountStatus status,

        @Schema(title = "Tipo da conta", example = "CORRENTE", type = "string", defaultValue = "CORRENTE", allowableValues = {"CORRENTE", "POUPANCA"})
        AccountType type,

        @Schema(description = "Data da última atualização do registro", example = "01-01-2024", type = "string", format = "date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDateTime updatedAt,

        @Schema(description = "Data de criação do registro", example = "01-01-2024", type = "string", format = "date")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDateTime createdAt

) {
}
