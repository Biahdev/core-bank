package dev.abeatriz.account_service.service;

import dev.abeatriz.account_service.entity.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumerService {

    @KafkaListener(topics = "new-transaction-topic")
    public void processarPagamento(Transaction transaction) {


        System.out.println("Transação consumida com sucesso!" + transaction);
    }
}
