package dev.abeatriz.transaction_service.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    private Long accounId;
    private BigDecimal bankBalance;
    private AccountStatus status;

}
