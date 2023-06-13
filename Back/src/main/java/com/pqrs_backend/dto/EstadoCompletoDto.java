package com.pqrs_backend.dto;

import com.pqrs_backend.entity.HistorialEstados;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCompletoDto {
    private int id_estado;

    @NotEmpty(message = "Se require un titulo.")
    private String titulo;

    @NotEmpty(message = "Se require una descripcion.")
    private String descripcion;

    private List<HistorialEstadosDto> historialEstados = new ArrayList<>();
}
