package com.pqrs_backend.dto;

import com.pqrs_backend.entity.HistorialEstados;
import com.pqrs_backend.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PqrsCompletoDto {

    private int id_radicado;

    @NotEmpty(message = "Se requiere un titulo.")
    private String titulo;

    @NotEmpty(message = "Se requiere una descripción.")
    private String descripcion;

    private String anexo;

    private UsuarioSinPasswordDto usuario;

    private PrioridadDto prioridad;

    private AreaDto area;

    private TipoDto tipo;

    private List<HistorialEstadosSinPqrsDto> historialEstadosList = new ArrayList<>();

    private List<RespuestaSinUsuarioDto> respuestas = new ArrayList<>();
}
