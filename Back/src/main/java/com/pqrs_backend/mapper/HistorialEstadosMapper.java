package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.HistorialEstadosDto;
import com.pqrs_backend.entity.HistorialEstados;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HistorialEstadosMapper {
    HistorialEstados toEntity(HistorialEstadosDto historialEstadosDto);

    HistorialEstadosDto toDto(HistorialEstados historialEstados);

    @Mapping(target = "id_historial", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(HistorialEstadosDto historialEstadosDto, @MappingTarget HistorialEstados historialEstados);
}
