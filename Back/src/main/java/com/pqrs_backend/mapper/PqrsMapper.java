package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.PqrsDto;
import com.pqrs_backend.entity.Pqrs;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PqrsMapper {
    Pqrs toEntity(PqrsDto pqrsDto);

    PqrsDto toDto(Pqrs pqrs);

}
