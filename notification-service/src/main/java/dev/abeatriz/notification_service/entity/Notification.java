package dev.abeatriz.notification_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Notification(NotificationChannel channel, String notes) {
        this.channel = channel;
        this.notes = notes;
    }
}
