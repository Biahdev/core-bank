package dev.abeatriz.account_service.mapper;

import dev.abeatriz.account_service.dto.AccountCreate;
import dev.abeatriz.account_service.dto.AccountDetail;
import dev.abeatriz.account_service.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDetail toDTO(Account entity);

    Account toEntity(AccountCreate dto);

    Account toEntity(AccountDetail dto);

    List<AccountDetail> toDTO(List<Account> entity);
}
