package com.pqrs_backend.service;

import com.pqrs_backend.dto.PrioridadCompletoDto;
import com.pqrs_backend.dto.PrioridadDto;
import com.pqrs_backend.entity.Prioridad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrioridadService {
    public List<PrioridadDto> listarPrioridades();

    public void guardar(PrioridadDto prioridadDto);

    public void eliminar(int id_prioridad);

    public PrioridadDto editarPrioridad(int id, PrioridadDto prioridadDto);

    public PrioridadCompletoDto encontrarPrioridad(int id_prioridad);
}
