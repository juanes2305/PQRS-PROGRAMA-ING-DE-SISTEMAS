package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDto {
    private int id_estado;

    @NotEmpty(message = "Se require un titulo.")
    private String titulo;

    @NotEmpty(message = "Se require una descripcion.")
    private String descripcion;
}
