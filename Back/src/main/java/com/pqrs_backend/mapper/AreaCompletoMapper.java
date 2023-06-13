package com.pqrs_backend.mapper;

import com.pqrs_backend.dto.AreaCompletoDto;
import com.pqrs_backend.entity.Area;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaCompletoMapper {
    Area toEntity(AreaCompletoDto areaCompletoDto);

    AreaCompletoDto toDto(Area area);
}
