package dev.abeatriz.transaction_service.mapper;

import dev.abeatriz.transaction_service.dto.TransactionDetailDTO;
import dev.abeatriz.transaction_service.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDetailDTO toDTO(Transaction clientEntity);
}
