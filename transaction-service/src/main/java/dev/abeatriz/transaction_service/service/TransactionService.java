package dev.abeatriz.transaction_service.service;

import dev.abeatriz.transaction_service.dto.NotificationMessageDTO;
import dev.abeatriz.transaction_service.dto.TransactionCreateDTO;
import dev.abeatriz.transaction_service.dto.TransactionDetailDTO;
import dev.abeatriz.transaction_service.entity.NotificationChannel;
import dev.abeatriz.transaction_service.entity.Transaction;
import dev.abeatriz.transaction_service.entity.TransactionStatus;
import dev.abeatriz.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, NotificationMessageDTO> kafkaTemplate;

    @Autowired
    private AccountClientFeign accountClientFeign;

    public TransactionDetailDTO create(TransactionCreateDTO json) {

        var newTansactionTopic = "new-transaction-topic";
        var sourceAccount = accountClientFeign.getById(json.sourceAccountId());
        var destinationAccount = accountClientFeign.getById(json.destinationAccountId());
        // trnaformar json.method em Enum, fazer um mapper para tranformar isso
        var newTransaction =  new Transaction(json.sourceAccountId(), json.destinationAccountId(), json.amount(), TransactionStatus.FAIL, json.method(), "");


        if(sourceAccount == null  || destinationAccount == null){
            // Lança um exception que o API Handler vai tratar
        }

        if(sourceAccount.balance().compareTo(json.amount()) > 0){
            // Lança exception informando que a conta origem não tem saúdo suficiente
            var notification = new NotificationMessageDTO(sourceAccount.accountId(), NotificationChannel.PUSH, "Saldo insuficiênte na conta origem");
            this.kafkaTemplate.send(newTansactionTopic, notification);

        }


        // Na transação, tipo metodo status accountId
        // criar transactionId nesse serviço

        // Validar se tem saldo, se tiver sucesso manda pra acount-service
        // caso contrario não mande para account-service e envie uma notificação para o usuario
        // informando que não tem saldo suficiente na conta

        json.setAccountId(1L);


        Notification notification;

        if (account.getBankBalance().compareTo(json.getAmount()) > 0) {
            notification = new Notification(json.getAccountId(), NotificationChannel.PUSH, "Parabéns! Transação concluída com sucesso!");
            json.setStatus(TransactionStatus.SUCESSO);


            kafkaTemplate.send(new_transaction_topic, notification);
            System.out.println("Tópico -> " + newTansactionTopic + " | Object -> " + json);

        } else {
            notification = new Notification(json.getAccountId(), NotificationChannel.PUSH, "Saldo insuficiente");
            json.setStatus(TransactionStatus.FALHA);
        }


        var newTansactionTopic = "new-notication-topic";
        kafkaTemplate.send(newTansactionTopic, notification);
        System.out.println("Tópico -> " + newTansactionTopic + " | Object -> " + notification);

    }


}
