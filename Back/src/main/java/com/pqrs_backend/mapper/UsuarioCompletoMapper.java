package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.UsuarioCompletoDto;
import com.pqrs_backend.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioCompletoMapper {
    Usuario toEntity(UsuarioCompletoDto usuarioCompletoDto);

    UsuarioCompletoDto toDto(Usuario usuario);
}
