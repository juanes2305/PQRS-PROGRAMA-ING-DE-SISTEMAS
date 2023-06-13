package com.pqrs_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pqrs_backend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PqrsDto {

    private int id_radicado;

    @NotEmpty(message = "Se requiere un titulo.")
    private String titulo;

    @NotEmpty(message = "Se requiere una descripción.")
    private String descripcion;

    private String anexo;

    private UsuarioSinPasswordDto usuario; //dto

    private PrioridadDto prioridad;

    private AreaDto area;

    private TipoDto tipo;
}
