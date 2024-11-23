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
@Table(name = "account_transactions")
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountTransactionId;
    private Long accountId;
    private Long transactionId;
    private BigDecimal amount;
    private BigDecimal balanceAfterTransaction;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public AccountTransaction(Long transactionId, Long accountId, BigDecimal amount, BigDecimal balanceAfterTransaction, TransactionType type) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.type = type;
    }
}
