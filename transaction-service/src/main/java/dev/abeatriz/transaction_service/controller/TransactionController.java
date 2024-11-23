package dev.abeatriz.transaction_service.controller;

import dev.abeatriz.transaction_service.dto.TransactionCreateDTO;
import dev.abeatriz.transaction_service.dto.TransactionDetailDTO;
import dev.abeatriz.transaction_service.entity.NotificationChannel;
import dev.abeatriz.transaction_service.entity.Transaction;
import dev.abeatriz.transaction_service.entity.TransactionStatus;
import dev.abeatriz.transaction_service.service.AccountClientFeign;
import dev.abeatriz.transaction_service.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transação", description = "Operações relacionadas entidade transação")
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public TransactionDetailDTO create(@RequestBody TransactionCreateDTO json) {
        return transactionService.create(json);
    }

}
