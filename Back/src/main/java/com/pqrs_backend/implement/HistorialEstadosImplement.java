package com.pqrs_backend.implement;

import com.pqrs_backend.dto.HistorialEstadosDto;
import com.pqrs_backend.entity.HistorialEstados;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.HistorialEstadosMapper;
import com.pqrs_backend.repository.HistorialEstadosRepository;
import com.pqrs_backend.service.HistorialEstadosService;
import com.pqrs_backend.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class HistorialEstadosImplement implements HistorialEstadosService {

    @Autowired
    private HistorialEstadosRepository historialEstadosRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private HistorialEstadosMapper historialEstadosMapper;


    @Override
    public List<HistorialEstadosDto> listarHistorialEstados() {
        List<HistorialEstadosDto> historialEstadosDtos = new ArrayList<>();
        List<HistorialEstados> historialEstados = historialEstadosRepository.findAll();
        if(!historialEstados.isEmpty()){
            for (HistorialEstados historialEstados1: historialEstados) {
                HistorialEstadosDto historialEstadoDto = historialEstadosMapper.toDto(historialEstados1);
                historialEstadosDtos.add(historialEstadoDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("historialEstadosEmpty",null, Locale.getDefault()));
        }
        return historialEstadosDtos;
    }

    @Override
    public void guardar(HistorialEstadosDto historialEstadosDto) {
            HistorialEstados historialEstado = historialEstadosMapper.toEntity(historialEstadosDto);
            historialEstadosRepository.save(historialEstado);
    }

    @Override
    public void eliminar(int id_historial) {
        historialEstadosRepository.findById(id_historial).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("historialEstadoNotFound",null, Locale.getDefault()))
        );
        historialEstadosRepository.deleteById(id_historial);
    }

    @Override
    public HistorialEstadosDto editarHistorialEstados(int id, HistorialEstadosDto historialEstadoDto) {
        HistorialEstados historialEstado = historialEstadosRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("historialEstadoNotFound",null, Locale.getDefault()))
        );
        historialEstadosMapper.updateEntity(historialEstadoDto,historialEstado);
        historialEstadosRepository.save(historialEstado);
        return historialEstadosMapper.toDto(historialEstado);
    }

    @Override
    public HistorialEstadosDto encontrarHistorialEstados(int id_historial) {
        return historialEstadosMapper.toDto(historialEstadosRepository.findById(id_historial).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("historialEstadoNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public List<HistorialEstadosDto> FiltroHistorial(int id_estado, int id_tipo) {
        List<HistorialEstados> historialEstados = historialEstadosRepository.filtroPorEstadoYTipo(id_estado, id_tipo);
        List<HistorialEstadosDto> historialEstadosDtos = new ArrayList<>();
        for (HistorialEstados historial: historialEstados
             ) {
            HistorialEstadosDto historialEstadosDto = historialEstadosMapper.toDto(historial);
            historialEstadosDtos.add(historialEstadosDto);
        }
        return historialEstadosDtos;
    }
}
