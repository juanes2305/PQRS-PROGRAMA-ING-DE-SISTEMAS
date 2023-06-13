package com.pqrs_backend.service;

import com.pqrs_backend.dto.HistorialEstadosDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistorialEstadosService {
    public List<HistorialEstadosDto> listarHistorialEstados();

    public void guardar(HistorialEstadosDto historialEstadosDto);

    public void eliminar(int id_historial);

    public HistorialEstadosDto editarHistorialEstados(int id, HistorialEstadosDto historialEstadosDto);

    public HistorialEstadosDto encontrarHistorialEstados(int id_historial);

    public List<HistorialEstadosDto> FiltroHistorial(int id_estado, int id_tipo);
}
