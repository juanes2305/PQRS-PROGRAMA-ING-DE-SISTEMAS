package com.pqrs_backend.dto;

import com.pqrs_backend.entity.Pqrs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrioridadCompletoDto {
    private int id_prioridad;

    @NotEmpty(message = "Se require una descripcion.")
    private String descripcion;

    private List<PqrsDto> pqrsList = new ArrayList<>();
}
