package com.pqrs_backend.service;

import com.pqrs_backend.dto.EstadoCompletoDto;
import com.pqrs_backend.dto.EstadoDto;
import com.pqrs_backend.entity.Estado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstadoService {
    public List<EstadoDto> listarEstados();

    public void guardar(EstadoDto estadoDto);

    public void eliminar(int id_estado);

    public EstadoDto editarEstado(int id, EstadoDto estadoDto);

    public EstadoCompletoDto encontrarEstado(int id_estado);
}
