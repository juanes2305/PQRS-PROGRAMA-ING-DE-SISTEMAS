package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.RespuestaDto;
import com.pqrs_backend.entity.Respuesta;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RespuestaMapper {
    Respuesta toEntity(RespuestaDto respuestaDto);

    RespuestaDto toDto(Respuesta respuesta);

}
