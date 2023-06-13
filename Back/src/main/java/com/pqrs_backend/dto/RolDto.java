package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDto {
    private int id_rol;

    @NotEmpty(message = "Se require una descripcion.")
    private String descripcion;
}
