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
public class TipoCompletoDto {

    private int id_tipo;

    @NotEmpty(message = "Se requiere el nombre del tipo")
    private String nombre;

    private List<PqrsDto> pqrsList = new ArrayList<>();
}
