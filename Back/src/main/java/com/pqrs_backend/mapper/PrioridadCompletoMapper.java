package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.PrioridadCompletoDto;
import com.pqrs_backend.entity.Prioridad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrioridadCompletoMapper {
    Prioridad toEntity(PrioridadCompletoDto prioridadCompletoDto);

    PrioridadCompletoDto toDto(Prioridad prioridad);
}
