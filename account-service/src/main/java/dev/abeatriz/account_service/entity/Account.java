package dev.abeatriz.account_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String name;
    private String document;
    private BigDecimal balance;
    private AccountStatus status;
    private AccountType type;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Account(String name, String document, BigDecimal balance, AccountStatus status, AccountType type) {
        this.name = name;
        this.document = document;
        this.balance = balance;
        this.status = status;
        this.type = type;
    }

}