package com.pqrs_backend.service;

import com.pqrs_backend.dto.TipoCompletoDto;
import com.pqrs_backend.dto.TipoDto;
import com.pqrs_backend.entity.Tipo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoService {
    public List<TipoDto> listarTipos();

    public void guardar(TipoDto tipoDto);

    public void eliminar(int id_tipo);

    public TipoDto editarTipo(int id, TipoDto tipo);

    public TipoCompletoDto encontrarTipo(int id_tipo);
}
