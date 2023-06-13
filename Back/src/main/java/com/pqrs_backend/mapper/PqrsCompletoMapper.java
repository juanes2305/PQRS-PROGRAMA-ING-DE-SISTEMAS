package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.PqrsCompletoDto;
import com.pqrs_backend.dto.PqrsDto;
import com.pqrs_backend.entity.Pqrs;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PqrsCompletoMapper {
    Pqrs toEntity(PqrsCompletoDto pqrsCompletoDto);

    PqrsCompletoDto toDto(Pqrs pqrs);

}
