package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.UsuarioSinPasswordDto;
import com.pqrs_backend.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioSinPasswordMapper {
    Usuario toEntity(UsuarioSinPasswordDto usuarioSinPasswordDto);

    UsuarioSinPasswordDto toDto(Usuario usuario);
}
