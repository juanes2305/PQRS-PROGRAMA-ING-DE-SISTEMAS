package com.pqrs_backend.service;

import com.pqrs_backend.dto.RespuestaDto;
import com.pqrs_backend.dto.UsuarioDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface RespuestaService {
    public List<RespuestaDto> listarRespuestas();

    public void guardar(RespuestaDto respuestaDto);

    public void eliminar(int id_respuesta);

    public RespuestaDto editarRespuesta(int id, RespuestaDto respuestaDto);

    public RespuestaDto encontrarRespuesta(int id_respuesta);

    public List<RespuestaDto> encontrarRespuestasUsuario(int id_usuario);
    public RespuestaDto loadImage(RespuestaDto respuestaDto) throws IOException;

    public RespuestaDto respuestaPorRadicado(int id_radicado);
}
