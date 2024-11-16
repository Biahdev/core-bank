package dev.abeatriz.account_service.service;

import dev.abeatriz.account_service.dto.TransactionMessageDTO;
import dev.abeatriz.account_service.entity.AccountTransaction;
import dev.abeatriz.account_service.entity.TransactionType;
import dev.abeatriz.account_service.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionConsumerService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @KafkaListener(topics = "new-transaction-topic")
    public void processarPagamento(TransactionMessageDTO transaction) {
        var balanceAfterTransaction = new BigDecimal(0);
        var account = accountService.getById(transaction.accountId());

        if (transaction.type() == TransactionType.RECEIPT) {
            balanceAfterTransaction = account.balance().add(transaction.amount());
        }

        if (transaction.type() == TransactionType.PAYMENT) {
            balanceAfterTransaction = account.balance().subtract(transaction.amount());
        }

        var newTransaction = new AccountTransaction(transaction.accountId(), transaction.transactionId(), transaction.amount(), balanceAfterTransaction, transaction.type(), transaction.method());
        accountTransactionRepository.save(newTransaction);

        System.out.println("Transação consumida com sucesso!" + newTransaction);
    }
}
