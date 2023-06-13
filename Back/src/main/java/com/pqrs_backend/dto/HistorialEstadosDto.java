package com.pqrs_backend.dto;

import com.pqrs_backend.entity.Estado;
import com.pqrs_backend.entity.Pqrs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialEstadosDto {

    private int id_historial;

    private LocalDateTime fecha_cambio;

    private PqrsDto pqrs;

    private EstadoDto estado;
}
