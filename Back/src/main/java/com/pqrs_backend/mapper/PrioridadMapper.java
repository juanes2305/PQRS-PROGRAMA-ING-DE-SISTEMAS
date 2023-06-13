package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.PrioridadDto;
import com.pqrs_backend.entity.Prioridad;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PrioridadMapper {
    Prioridad toEntity(PrioridadDto prioridadDto);

    PrioridadDto toDto(Prioridad prioridad);

    @Mapping(target = "id_prioridad", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PrioridadDto prioridadDto, @MappingTarget Prioridad prioridad);
}
