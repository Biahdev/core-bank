package dev.abeatriz.account_service.service;

import dev.abeatriz.account_service.dto.NotificationMessageDTO;
import dev.abeatriz.account_service.entity.NotificationChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    @Autowired
    private final KafkaTemplate<String, NotificationMessageDTO> kafkaTemplate;
    private final String kafkaTopic = "new-notication-topic";

    public void create(Long id, NotificationChannel channel, String message) {
        var notification = new NotificationMessageDTO(id, channel, message);
        kafkaTemplate.send(this.kafkaTopic, notification);
        System.out.println("Tópico -> " + this.kafkaTopic + " | Object -> " + notification);
    }
}
