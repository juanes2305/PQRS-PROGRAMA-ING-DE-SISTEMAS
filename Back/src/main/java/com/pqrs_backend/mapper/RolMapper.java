package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.RolDto;
import com.pqrs_backend.entity.Rol;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RolMapper {
    Rol toEntity(RolDto rolDto);

    RolDto toDto(Rol rol);

    @Mapping(target = "id_rol", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RolDto rolDto, @MappingTarget Rol rol);
}
