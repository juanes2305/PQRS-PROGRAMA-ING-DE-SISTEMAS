package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.TipoDto;
import com.pqrs_backend.entity.Tipo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoMapper {
    Tipo toEntity(TipoDto tipoDto);

    TipoDto toDto(Tipo tipo);

    @Mapping(target = "id_tipo", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(TipoDto tipoDto, @MappingTarget Tipo tipo);
}
