package dev.abeatriz.transaction_service.controller;


import dev.abeatriz.transaction_service.entity.Notification;
import dev.abeatriz.transaction_service.entity.NotificationChannel;
import dev.abeatriz.transaction_service.entity.Transaction;
import dev.abeatriz.transaction_service.entity.TransactionStatus;
import dev.abeatriz.transaction_service.service.AccountClientFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private AccountClientFeign accountClientFeign;

    @PostMapping
    public Transaction newTransaction(@RequestBody Transaction json) {

        json.setAccountId(1L);

        var account = accountClientFeign.getById(json.getAccountId());
        System.out.println(account);
        System.out.println("---------------");

        Notification notification;

        if (account.getBankBalance().compareTo(json.getAmount()) > 0) {
            notification = new Notification(json.getAccountId(), NotificationChannel.PUSH, "Parabéns! Transação concluída com sucesso!");
            json.setStatus(TransactionStatus.SUCESSO);

            var new_transaction_topic = "new-transaction-topic";
            kafkaTemplate.send(new_transaction_topic, notification);
            System.out.println("Tópico -> " + new_transaction_topic + " | Object -> " + json);

        } else {
            notification = new Notification(json.getAccountId(), NotificationChannel.PUSH, "Saldo insuficiente");
            json.setStatus(TransactionStatus.FALHA);
        }


        var notification_topic = "new-notication-topic";
        kafkaTemplate.send(notification_topic, notification);
        System.out.println("Tópico -> " + notification_topic + " | Object -> " + notification);

        return json;
    }


}
