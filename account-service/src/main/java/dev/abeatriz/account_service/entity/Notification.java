package dev.abeatriz.account_service.entity;


import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    private Long accountId;
    private NotificationChannel channe;
    private String notes;
}
