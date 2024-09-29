package dev.abeatriz.account_service.entity;


import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    private Long transactionId;
    private Long accountId;
    private BigDecimal amount;
    private TransactionStatus status;


}
