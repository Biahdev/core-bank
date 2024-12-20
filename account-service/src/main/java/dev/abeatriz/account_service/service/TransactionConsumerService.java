package dev.abeatriz.account_service.service;

import dev.abeatriz.account_service.entity.AccountTransaction;
import dev.abeatriz.account_service.dto.NotificationMessage;
import dev.abeatriz.account_service.entity.NotificationChannel;
import dev.abeatriz.account_service.entity.TransactionType;
import dev.abeatriz.account_service.mapper.AccountMapper;
import dev.abeatriz.account_service.repository.AccountRepository;
import dev.abeatriz.account_service.repository.AccountTransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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
    private AccountMapper accountMapper;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private KafkaTemplate<String, NotificationMessage> kafkaNotification;

    @KafkaListener(topics = "new-transaction-topic")
    public void processarPagamento(TransactionAccountMessage transaction) {
        var sourceAccount = accountMapper.toEntity(accountService.listById(transaction.sourceAccountId()));
        var destinationAccount = accountMapper.toEntity(accountService.listById(transaction.destinationAccountId()));

        var newSourceAmount = sourceAccount.getBalance().subtract(transaction.amount());
        sourceAccount.setBalance(newSourceAmount);
        accountRepository.save(sourceAccount);

        var newDestinationAmount = destinationAccount.getBalance().add(transaction.amount());
        destinationAccount.setBalance(newDestinationAmount);
        accountRepository.save(destinationAccount);

        var newTransactionSource = new AccountTransaction(transaction.sourceAccountId(), transaction.transactionId(), transaction.amount(), newSourceAmount, TransactionType.PAYMENT);
        accountTransactionRepository.save(newTransactionSource);

        var newDestinationSource = new AccountTransaction(transaction.destinationAccountId(), transaction.transactionId(), transaction.amount(), newDestinationAmount, TransactionType.RECEIPT);
        accountTransactionRepository.save(newDestinationSource);

        var newNotificationTopic = "new-notication-topic";
        var notificationSourceMessage = new NotificationMessage(transaction.sourceAccountId(), NotificationChannel.PUSH, "Transação enviada com sucesso!");
        kafkaNotification.send(newNotificationTopic, notificationSourceMessage);

        var notificationDestinationMessage = new NotificationMessage(transaction.destinationAccountId(), NotificationChannel.PUSH, "Você recebeu uma transação de R$" + transaction.amount());
        kafkaNotification.send(newNotificationTopic, notificationDestinationMessage);
        ;
    }
}
