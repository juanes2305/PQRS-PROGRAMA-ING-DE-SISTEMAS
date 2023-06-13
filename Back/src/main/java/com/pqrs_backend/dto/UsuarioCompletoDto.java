package com.pqrs_backend.dto;

import com.pqrs_backend.entity.Pqrs;
import com.pqrs_backend.entity.Respuesta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCompletoDto {
    private int id_usuario;

    @NotEmpty(message = "Se requiere el nombre.")
    private String nombre;

    @NotEmpty(message = "Se requiere el apellido.")
    private String apellido;

    @NotEmpty(message = "Se requiere el telefono.")
    private String telefono;

    @NotEmpty(message = "Se requiere el email.")
    private String email;

    private String imagen;

    private boolean estado;

    private boolean confirmado;

    private RolDto rol;

    private List<PqrsSinUsuarioDto> pqrsList = new ArrayList<>();

    private List<RespuestaSinUsuarioDto> respuestas = new ArrayList<>();
}
