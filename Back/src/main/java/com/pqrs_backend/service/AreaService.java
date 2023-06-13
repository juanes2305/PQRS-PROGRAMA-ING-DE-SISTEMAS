package com.pqrs_backend.service;

import com.pqrs_backend.dto.AreaCompletoDto;
import com.pqrs_backend.dto.AreaDto;
import com.pqrs_backend.entity.Area;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AreaService {
    public List<AreaDto> listarAreas();

    public void guardar(AreaDto areaDto);

    public void eliminar(int id_area);


    public AreaDto editarArea(int id, AreaDto areaDto);

    public AreaCompletoDto encontrarArea(int id_area);
}
