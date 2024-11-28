package dev.abeatriz.account_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Payload para criação de nova conta bancária",
        title = "Requisição de Criação de Conta Bancária")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountCreate(

        @NotBlank
        @Size(min = 3, max = 150)
        @Schema(title = "Nome Completo do Titular", example = "Ana Beatriz", type = "string", minLength = 3, maxLength = 150)
        String name,

        @NotBlank
        @Size(min = 3, max = 50)
        @Schema(title = "Documento de Identificação", example = "123.231.123-23", type = "string", minLength = 3, maxLength = 50)
        String document,

        @Schema(title = "Saldo Inicial", example = "15.00", type = "number", format = "double")
        BigDecimal balance
) {
}
