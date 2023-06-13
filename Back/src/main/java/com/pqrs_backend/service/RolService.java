package com.pqrs_backend.service;

import com.pqrs_backend.dto.RolCompletoDto;
import com.pqrs_backend.dto.RolDto;
import com.pqrs_backend.entity.Rol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolService {
    public List<RolDto> listarRoles();

    public void guardar(RolDto rolDto);

    public void eliminar(int id_rol);

    public RolDto editarRol(int id, RolDto rolDto);

    public RolCompletoDto encontrarRol(int id_rol);
}
