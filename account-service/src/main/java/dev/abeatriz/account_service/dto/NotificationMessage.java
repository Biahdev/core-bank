package dev.abeatriz.account_service.dto;

import dev.abeatriz.account_service.entity.NotificationChannel;
import dev.abeatriz.account_service.exception.enums.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        title = "Mensagem de Notificação",
        description = "Objeto utilizado para enviar mensagens de notificação para o serviço de notificações via Kafka. Contém informações sobre a conta, o canal de notificação e as mensagens associadas."
)
public record NotificationMessage(
        @Schema(title = "ID da conta", example = "1", type = "long", minimum = "1")
        Long accountId,

        @ValueOfEnum(enumClass = NotificationChannel.class)
        @Schema(title = "Canal de Notificação", example = "EMAIL", type = "string", allowableValues = {"EMAIL", "PUSH", "SMS", "ALL"})
        NotificationChannel channel,

        @Schema(
                title = "Comentário da Notificação",
                description = "Texto da notificação a ser enviado ao destinatário. Pode ser uma mensagem informativa, de alerta ou parabenização.",
                example = "Parabéns! Transação concluída com sucesso.", type = "string", minLength = 1, maxLength = 250
        )
        String notes
) {
}
