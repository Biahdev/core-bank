package dev.abeatriz.transaction_service.mapper;

import dev.abeatriz.transaction_service.dto.TransactionDetailDTO;
import dev.abeatriz.transaction_service.entity.Transaction;
import dev.abeatriz.transaction_service.entity.TransactionMethod;
import dev.abeatriz.transaction_service.entity.TransactionStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDetailDTO toDTO(Transaction entity);

    String toString(TransactionMethod status);

    TransactionMethod toEnumMethod(String status);

    String toString(TransactionStatus status);

    TransactionStatus toEnumStatus(String status);
}
