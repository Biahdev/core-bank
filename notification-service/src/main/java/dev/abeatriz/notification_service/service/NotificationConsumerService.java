package dev.abeatriz.notification_service.service;

import dev.abeatriz.notification_service.entity.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {

    @KafkaListener(topics = "new-notication-topic")
    public void processarPagamento(Notification notification) {

        System.out.println("Notificação consumida com sucesso!" + notification);

    }
}
