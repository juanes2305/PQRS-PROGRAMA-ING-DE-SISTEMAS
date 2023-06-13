package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.EstadoCompletoDto;
import com.pqrs_backend.entity.Estado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoCompletoMapper {

    Estado toEntity(EstadoCompletoDto estadoCompletoDto);

    EstadoCompletoDto toDto(Estado estado);
}
