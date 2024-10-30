package dev.abeatriz.notification_service.service;

import dev.abeatriz.notification_service.entity.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {

    @KafkaListener(topics = "new-notication-topic")
    @RetryableTopic(
            backoff = @Backoff(value = 3000L), // Quanto tempo depois do erro que vai fazer a retentativas
            attempts = "5", // Quantidade de tentativas
            autoCreateTopics = "true", // Pode regriar os tópicos da retentativa, padrão é true
            include = Exception.class // Quando lançar essa vai mandar para a retentativa
    )
    public void processarPagamento(Notification notification) throws Exception {
        System.out.println("Notificação consumida com sucesso!" + notification);
//        throw new Exception();
    }
}
