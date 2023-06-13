package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private int id_usuario;

    @NotEmpty(message = "Se requiere el nombre.")
    private String nombre;

    @NotEmpty(message = "Se requiere el apellido.")
    private String apellido;

    @NotEmpty(message = "Se requiere el telefono.")
    private String telefono;

    @NotEmpty(message = "Se requiere el email.")
    private String email;

    @NotEmpty(message = "Se requiere el password.")
    private String password;

    private String imagen;

    private boolean estado;

    private boolean confirmado;

    private RolDto rol;
}
