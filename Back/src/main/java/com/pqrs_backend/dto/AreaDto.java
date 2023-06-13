package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto {

    private int id_area;

    @NotEmpty(message = "Se requiere el nombre del musculo")
    private String nombre;
}
