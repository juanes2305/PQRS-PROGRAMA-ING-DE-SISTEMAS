package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PqrsSinUsuarioDto {

    private int id_radicado;

    @NotEmpty(message = "Se requiere un titulo.")
    private String titulo;

    @NotEmpty(message = "Se requiere una descripción.")
    private String descripcion;

    private String anexo;

    private PrioridadDto prioridad;

    private AreaDto area;

    private TipoDto tipo;
}
