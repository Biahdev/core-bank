package dev.abeatriz.account_service.service;


import dev.abeatriz.account_service.dto.AccountCreate;
import dev.abeatriz.account_service.dto.AccountDetail;
import dev.abeatriz.account_service.entity.Account;
import dev.abeatriz.account_service.entity.AccountStatus;
import dev.abeatriz.account_service.entity.AccountType;
import dev.abeatriz.account_service.entity.NotificationChannel;
import dev.abeatriz.account_service.mapper.AccountMapper;
import dev.abeatriz.account_service.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public AccountDetail create(AccountCreate json) {
        var newAccount = new Account(json.name(), json.document(), new BigDecimal(0), AccountStatus.ATIVO, AccountType.CORRENTE);
        var accountEntity = accountRepository.save(newAccount);

        if (accountEntity.getAccountId() == null) throw new RuntimeException();

        notificationService.create(accountEntity.getAccountId(), NotificationChannel.EMAIL, "Parabéns! Conta criada com sucesso");
        notificationService.create(accountEntity.getAccountId(), NotificationChannel.SMS, "Parabéns! Conta criada com sucesso acesse o email para mais informações");

        return accountMapper.toDTO(accountEntity);
    }

    @Transactional(readOnly = true)
    public AccountDetail getById(Long id) {
        var account = accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return accountMapper.toDTO(account);
    }
}
