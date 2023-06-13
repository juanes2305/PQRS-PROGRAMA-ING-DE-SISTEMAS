package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.UsuarioDto;
import com.pqrs_backend.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto usuarioDto);
    UsuarioDto toDto(Usuario usuario);
}
