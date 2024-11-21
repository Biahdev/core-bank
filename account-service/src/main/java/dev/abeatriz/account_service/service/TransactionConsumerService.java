package dev.abeatriz.account_service.service;

import dev.abeatriz.account_service.entity.AccountTransaction;
import dev.abeatriz.account_service.dto.NotificationMessage;
import dev.abeatriz.account_service.repository.AccountRepository;
import dev.abeatriz.account_service.repository.AccountTransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import dev.abeatriz.account_service.dto.TransactionAccountMessage;

import java.math.BigDecimal;

@Service
public class TransactionConsumerService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @KafkaListener(topics = "new-transaction-topic")
    public void processarPagamento(TransactionAccountMessage transaction) {
        var sourceAccount = accountRepository.findById(transaction.sourceAccountId()).orElseThrow(EntityNotFoundException::new);
        var destinationAccount = accountRepository.findById(transaction.destinationAccountId()).orElseThrow(EntityNotFoundException::new);

        var newSourceAmount = sourceAccount.getBalance().subtract(transaction.amount());
        sourceAccount.setBalance(newSourceAmount);
        accountRepository.save(sourceAccount);

        var newDestinationAmount = destinationAccount.getBalance().add(transaction.amount());
        destinationAccount.setBalance(newDestinationAmount);
        accountRepository.save(destinationAccount);

        var newTransactionSource = new AccountTransaction(transaction.sourceAccountId(), transaction.transactionId(), transaction.amount(), newSourceAmount);
        accountTransactionRepository.save(newTransactionSource);

        var newDestinationSource = new AccountTransaction(transaction.destinationAccountId(), transaction.transactionId(), transaction.amount(), newDestinationAmount);
        accountTransactionRepository.save(newDestinationSource);

        //TODO: Diminuir do valor do Account
        var notificationSourceMessage = new NotificationMessage(json.sourceAccountId(), NotificationChannel.PUSH, "Transação enviada com sucesso!");
        kafkaNotification.send(newNotificationTopic, notificationSourceMessage);

        var notificationDestinationMessage = new NotificationMessage(json.destinationAccountId(), NotificationChannel.PUSH, "Você recebeu uma transação de R$" + json.amount());
        kafkaNotification.send(newNotificationTopic, notificationMessage);

        System.out.println("Transação consumida com sucesso!" + newTransaction);
    }
}
