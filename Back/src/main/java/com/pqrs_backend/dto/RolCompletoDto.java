package com.pqrs_backend.dto;

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
public class RolCompletoDto {
    private int id_rol;

    @NotEmpty(message = "Se require una descripcion.")
    private String descripcion;

    private List<UsuarioSinPasswordDto> usuarios = new ArrayList<>();
}
