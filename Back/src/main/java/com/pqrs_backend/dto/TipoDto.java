package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDto {

    private int id_tipo;

    @NotEmpty(message = "Se requiere el nombre del tipo")
    private String nombre;
}
