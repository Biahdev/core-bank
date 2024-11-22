package dev.abeatriz.account_service.mapper;

import dev.abeatriz.account_service.dto.AccountDetail;
import dev.abeatriz.account_service.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDetail toDTO(Account clientEntity);
}
