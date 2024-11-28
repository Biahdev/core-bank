package dev.abeatriz.account_service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({"timestamp", "status", "message", "fields"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@Schema(
        title = "Mensagem de Erro",
        description = "Objeto que representa a mensagem de erro retornada pela API, incluindo o status da requisição, a mensagem de erro e informações adicionais sobre os campos que causaram o erro, se houver."
)
public class ErrorMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(title = "Timestamp do Erro", example = "27/11/2024 15:30:45", type = "string")
    private LocalDateTime timestamp;

    @Schema(title = "Status HTTP", example = "400", type = "integer")
    private int status;

    @Schema(title = "Mensagem de Erro", example = "Os Campos informados estão inválidos", type = "string")
    private String message;

    @Schema(title = "Campos de Erro", example = "{\"nome\": [\"O nome não pode estar em branco\"]}", type = "object")
    private Map<String, List<String>> fields;

    public ErrorMessage(String message, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.message = message;
    }

    public ErrorMessage(String message, HttpStatus status, Map<String, List<String>> fields) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.message = message;
        this.fields = fields;
    }
}