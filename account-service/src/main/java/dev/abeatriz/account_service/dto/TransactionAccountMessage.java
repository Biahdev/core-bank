package dev.abeatriz.account_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

@Schema(
    title = "Mensagem de Transação de Conta",
    description = "Objeto utilizado para representar os detalhes de uma transação entre duas contas bancárias. Inclui os identificadores das contas envolvidas, o ID da transação e o valor da operação."
)
public record TransactionAccountMessage(
        @Min(1)
        @Schema(title = "ID da conta origem", example = "1", type = "long", minimum = "1")
        Long sourceAccountId,

        @Min(1)
        @Schema(title = "ID da conta destino", example = "1", type = "long", minimum = "1")
        Long destinationAccountId,

        @Min(1)
        @Schema(title = "ID da transação", example = "1", type = "long", minimum = "1")
        Long transactionId,

        @Min(1)
        @Schema(title = "Valor da transação", description = "Valor monetário da transação, em formato decimal. Deve ser um valor positivo.", example = "15.00", type = "double")
        BigDecimal amount
) {
}
