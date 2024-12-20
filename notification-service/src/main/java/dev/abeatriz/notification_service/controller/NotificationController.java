package dev.abeatriz.notification_service.controller;

import dev.abeatriz.notification_service.entity.Notification;
import dev.abeatriz.notification_service.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notificação", description = "Operações relacionadas entidade Notificação")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> listAll() {
        return notificationService.listAll();
    }

    @PatchMapping("/{id}/read")
    public Notification read(@PathVariable Long id){
        return notificationService.read(id);
    }


}
