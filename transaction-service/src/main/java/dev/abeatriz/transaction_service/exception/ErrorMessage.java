package dev.abeatriz.transaction_service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
public class ErrorMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String message;
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