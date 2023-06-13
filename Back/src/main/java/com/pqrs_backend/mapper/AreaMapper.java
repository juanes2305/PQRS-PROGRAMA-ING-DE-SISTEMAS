package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.AreaDto;
import com.pqrs_backend.entity.Area;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    Area toEntity(AreaDto areaDto);

    AreaDto toDto(Area area);

    @Mapping(target = "id_area", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(AreaDto areaDto, @MappingTarget Area area);
}
