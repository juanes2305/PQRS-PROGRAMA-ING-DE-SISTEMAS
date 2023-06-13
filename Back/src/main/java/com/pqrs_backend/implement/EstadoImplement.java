package com.pqrs_backend.implement;

import com.pqrs_backend.dto.EstadoCompletoDto;
import com.pqrs_backend.dto.EstadoDto;
import com.pqrs_backend.entity.Estado;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.EstadoCompletoMapper;
import com.pqrs_backend.mapper.EstadoMapper;
import com.pqrs_backend.repository.EstadoRepository;
import com.pqrs_backend.service.EstadoService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EstadoImplement implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private EstadoMapper estadoMapper;

    @Autowired
    private EstadoCompletoMapper estadoCompletoMapper;

    @Override
    public List<EstadoDto> listarEstados() {
        List<EstadoDto> estadoDtos = new ArrayList<>();
        List<Estado> estados = estadoRepository.findAll();
        if(!estados.isEmpty()){
            for (Estado estado: estados) {
                EstadoDto estadoDto = estadoMapper.toDto(estado);
                estadoDtos.add(estadoDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("estadosNotFound",null, Locale.getDefault()));
        }
        return estadoDtos;
    }

    @Override
    public void guardar(EstadoDto estadoDto) {
            Estado estado = estadoMapper.toEntity(estadoDto);
            estadoRepository.save(estado);
    }

    @Override
    public void eliminar(int id_estado) {
        estadoRepository.findById(id_estado).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("estadoNotFound",null, Locale.getDefault()))
        );
        estadoRepository.deleteById(id_estado);
    }

    @Override
    public EstadoDto editarEstado(int id, EstadoDto estadoDto) {
        Estado estado = estadoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("estadoNotFound",null, Locale.getDefault()))
        );
        estadoMapper.updateEntity(estadoDto,estado);
        estadoRepository.save(estado);
        return estadoMapper.toDto(estado);
    }

    @Override
    public EstadoCompletoDto encontrarEstado(int id_estado) {
        return estadoCompletoMapper.toDto(estadoRepository.findById(id_estado).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("estadoNotFound",null, Locale.getDefault()))
        ));
    }
}
