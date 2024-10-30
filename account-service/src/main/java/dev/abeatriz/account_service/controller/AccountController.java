package dev.abeatriz.account_service.controller;

import dev.abeatriz.account_service.entity.Account;
import dev.abeatriz.account_service.entity.AccountStatus;
import dev.abeatriz.account_service.entity.Notification;
import dev.abeatriz.account_service.entity.NotificationChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final KafkaTemplate<String, Notification> kafkaTemplate;

    @PostMapping
    public Account newAccount(@RequestBody Account json) {
        json.setAccounId(1L);

        var notification = new Notification(json.getAccounId(), NotificationChannel.EMAIL, "Parabéns! Sua nova conta foi criada com sucesso!!!!!");
        var nome_topico = "new-notication-topic";
        kafkaTemplate.send(nome_topico, notification);
        System.out.println("Tópico -> " + nome_topico + " | Object -> " + notification);

        return json;
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable("id") Long id) {
        return new Account(1L, new BigDecimal(100), AccountStatus.ATIVO);
    }


}
