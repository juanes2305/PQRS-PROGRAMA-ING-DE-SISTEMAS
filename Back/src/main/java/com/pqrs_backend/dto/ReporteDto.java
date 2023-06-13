package com.pqrs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDto {
    private int id_radicado;

    private String titulo;

    private String descripcion;

    private String anexo;

    private String usuario;

    private String prioridad;

    private String area;

    private String tipo;

    private String estado;

    public ReporteDto(int id_radicado, String titulo, String area, String tipo, String prioridad, String estado) {
        this.id_radicado = id_radicado;
        this.titulo = titulo;
        this.area = area;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = estado;
    }
}
