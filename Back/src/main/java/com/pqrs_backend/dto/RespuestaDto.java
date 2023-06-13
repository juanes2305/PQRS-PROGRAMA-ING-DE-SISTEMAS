package com.pqrs_backend.dto;

import com.pqrs_backend.entity.Pqrs;
import com.pqrs_backend.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDto {

    private int id_respuesta;

    @NotEmpty(message = "Se requiere dar una respuesta")
    private String respuesta;

    @Positive
    private double calificacion;

    private String anexo;

    private UsuarioSinPasswordDto usuario;

    private PqrsDto pqrs;

}
