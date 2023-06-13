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
public class AreaCompletoDto {
    private int id_area;

    @NotEmpty(message = "Se requiere el nombre del musculo")
    private String nombre;

    private List<PqrsDto> pqrsList = new ArrayList<>();
}
