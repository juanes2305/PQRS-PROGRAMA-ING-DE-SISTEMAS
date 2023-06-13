package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialEstadosSinPqrsDto {

    private int id_historial;

    private LocalDateTime fecha_cambio;

    private EstadoDto estado;
}
