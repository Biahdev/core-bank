package dev.abeatriz.account_service.entity;

import dev.abeatriz.account_service.dto.AccountCreate;
import dev.abeatriz.account_service.dto.AccountUpdate;
import dev.abeatriz.account_service.service.AccountService;
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
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String name;
    private String document;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
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

    public void update(AccountUpdate json) {
        this.name = json.name() != null ? json.name() : this.name;
        this.document = json.document() != null ? json.document() : this.document;
        this.status = json.status() != null ? json.status() : this.status;
        this.type = json.type() != null ? json.type() : this.type;
    }


}