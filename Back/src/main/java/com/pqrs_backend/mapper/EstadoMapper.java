package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.EstadoDto;
import com.pqrs_backend.entity.Estado;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    Estado toEntity(EstadoDto estadoDto);

    EstadoDto toDto(Estado estado);

    @Mapping(target = "id_estado", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(EstadoDto estadoDto, @MappingTarget Estado estado);
}
