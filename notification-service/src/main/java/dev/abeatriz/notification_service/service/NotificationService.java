package dev.abeatriz.notification_service.service;

import dev.abeatriz.notification_service.entity.Notification;
import dev.abeatriz.notification_service.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> listAll() {
        return notificationRepository.findAll();
    }

    public Notification read(Long id){
        var notification = notificationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        notification.setIsRead(true);
        notificationRepository.save(notification);
        return notification;
    }

    @KafkaListener(topics = "new-notication-topic")
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "5",
            autoCreateTopics = "true",
            include = Exception.class
    )
    public void processarPagamento(Notification notification) throws Exception {
        var newNotification = new Notification(notification.getChannel(), notification.getNotes());
        notificationRepository.save(newNotification);
        System.out.println("Notificação consumida com sucesso!" + notification);
    }


}
