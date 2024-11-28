package dev.abeatriz.account_service.service;

import dev.abeatriz.account_service.dto.AccountCreate;
import dev.abeatriz.account_service.dto.AccountDetail;
import dev.abeatriz.account_service.dto.AccountUpdate;
import dev.abeatriz.account_service.entity.Account;
import dev.abeatriz.account_service.entity.AccountStatus;
import dev.abeatriz.account_service.entity.AccountType;
import dev.abeatriz.account_service.entity.NotificationChannel;
import dev.abeatriz.account_service.mapper.AccountMapper;
import dev.abeatriz.account_service.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private AccountMapper mapperMock;

    private final AccountMapper mapper = AccountMapper.INSTANCE;

    AccountCreate accountCreate;
    AccountUpdate accountUpdate;
    Account accountEntity;
    AccountDetail accountDetail;

    @BeforeEach
    void setup() {
        accountCreate = new AccountCreate("Ana Beatriz", "234144123", new BigDecimal("30.0"));
        accountEntity = mapper.toEntity(accountCreate);
        accountEntity.setAccountId(1L);
        accountEntity.setType(AccountType.CORRENTE);
        accountEntity.setStatus(AccountStatus.ATIVO);
        accountDetail = mapper.toDTO(accountEntity);
        accountUpdate = new AccountUpdate("Cecilia", "1231241212", AccountStatus.ATIVO, AccountType.POUPANCA);
    }

    @Test
    void testCreateAccountWithValidInputs() {
        // Given
        given(accountRepository.save(any(Account.class))).willReturn(accountEntity);
        given(mapperMock.toDTO(accountEntity)).willReturn(accountDetail);

        // When
        var result = accountService.create(accountCreate);

        // Then
        assertNotNull(result);
        assertEquals(accountDetail, result);
        assertEquals(AccountStatus.ATIVO, accountDetail.status());
        assertEquals(AccountType.CORRENTE, accountDetail.type());

        verify(notificationService, times(1)).create(1L, NotificationChannel.EMAIL, "Parabéns! Conta criada com sucesso");
        verify(notificationService, times(1)).create(1L, NotificationChannel.SMS, "Parabéns! Conta criada com sucesso acesse o email para mais informações");
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(mapperMock, times(1)).toDTO(accountEntity);
    }

    @Test
    void testUpdateAccountNotFound() {
        given(accountRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.update(1L, accountUpdate));

        verify(notificationService, times(0)).create(anyLong(), any(NotificationChannel.class), anyString());
    }


    @Test
    void testDisableAccountNotFound() {
        given(accountRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.disable(1L));

        verify(notificationService, times(0)).create(anyLong(), any(NotificationChannel.class), anyString());
    }

    @Test
    void testListByIdNotFound() {
        given(accountRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.listById(1L));
    }


}
