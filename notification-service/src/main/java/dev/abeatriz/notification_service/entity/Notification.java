package dev.abeatriz.notification_service.entity;


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