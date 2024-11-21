package dev.abeatriz.transaction_service.service;

import dev.abeatriz.transaction_service.dto.*;
import dev.abeatriz.transaction_service.entity.NotificationChannel;
import dev.abeatriz.transaction_service.entity.Transaction;
import dev.abeatriz.transaction_service.entity.TransactionStatus;
import dev.abeatriz.transaction_service.mapper.TransactionMapper;
import dev.abeatriz.transaction_service.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private KafkaTemplate<String, NotificationMessageDTO> kafkaNotification;

    @Autowired
    private KafkaTemplate<String, TransactionAccountMessageDTO> kafkaTransaction;

    @Autowired
    private AccountClientFeign accountClientFeign;

    private final String newTansactionTopic = "new-transaction-topic";
    private final String newNotificationTopic = "new-notication-topic";


    public TransactionDetailDTO create(TransactionCreateDTO json) {
        var sourceAccount = accountClientFeign.getById(json.sourceAccountId());
        var destinationAccount = accountClientFeign.getById(json.destinationAccountId());
        var newTransaction = new Transaction(json.sourceAccountId(), json.destinationAccountId(), json.amount(), TransactionStatus.PENDING, transactionMapper.toEnumMethod(json.method()), "");

        if (sourceAccount == null || destinationAccount == null) {
            newTransaction.setStatus(TransactionStatus.FAIL);
            transactionRepository.save(newTransaction);
            throw new EntityNotFoundException("Não foi encontrado as contas selecionadas");
        }

        if (sourceAccount.balance().compareTo(json.amount()) < 0) {
            newTransaction.setStatus(TransactionStatus.FAIL);
            transactionRepository.save(newTransaction);
            var notification = new NotificationMessageDTO(sourceAccount.accountId(), NotificationChannel.PUSH, "Transação não foi concluída, saldo insuficiênte");
            kafkaNotification.send(newNotificationTopic, notification);
            throw new EntityNotFoundException("Saldo insuficiênte na conta origem");
        }

        newTransaction.setStatus(TransactionStatus.SUCCESS);
        var save = transactionRepository.save(newTransaction);

        var transaction = new TransactionAccountMessageDTO(json.sourceAccountId(), json.destinationAccountId(), save.getTransactionId(), json.amount());
        kafkaTransaction.send(newTansactionTopic, transaction);

        return transactionMapper.toDTO(save);
    }
}
