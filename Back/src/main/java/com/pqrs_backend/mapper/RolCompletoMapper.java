package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.EstadoCompletoDto;
import com.pqrs_backend.dto.RolCompletoDto;
import com.pqrs_backend.entity.Estado;
import com.pqrs_backend.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolCompletoMapper {
    Estado toEntity(RolCompletoDto rolCompletoDto);

    RolCompletoDto toDto(Rol rol);
}
