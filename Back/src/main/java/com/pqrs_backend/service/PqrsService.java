package com.pqrs_backend.service;

import com.pqrs_backend.dto.EstadoDto;
import com.pqrs_backend.dto.PqrsCompletoDto;
import com.pqrs_backend.dto.PqrsDto;
import com.pqrs_backend.dto.UsuarioDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PqrsService {
    public List<PqrsDto> listarPQRS();

    public List<PqrsDto> listarUltimosPQRS();

    public List<PqrsCompletoDto> listarRadicados(int id_tipo);

    public void guardar(PqrsDto pqrsDto);

    public void eliminar(int id_radicado);

    public PqrsDto editarPQRS(int id, PqrsDto pqrsDto) throws IOException;

    public void cambiarEstado(int id, EstadoDto estadoDto);

    public PqrsCompletoDto encontrarPQRS(int id_radicado);

    public String encontrarEstado(int id_radicado);

    public PqrsDto loadImage(PqrsDto pqrsDto) throws IOException;
}
