package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.TipoCompletoDto;
import com.pqrs_backend.entity.Tipo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoCompletoMapper {
    Tipo toEntity(TipoCompletoDto tipoCompletoDto);

    TipoCompletoDto toDto(Tipo tipo);
}
