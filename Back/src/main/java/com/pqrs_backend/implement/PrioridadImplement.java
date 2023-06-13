package com.pqrs_backend.implement;

import com.pqrs_backend.dto.PrioridadCompletoDto;
import com.pqrs_backend.dto.PrioridadDto;
import com.pqrs_backend.entity.Prioridad;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.PrioridadCompletoMapper;
import com.pqrs_backend.mapper.PrioridadMapper;
import com.pqrs_backend.repository.PrioridadRepository;
import com.pqrs_backend.service.PrioridadService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PrioridadImplement implements PrioridadService {

    @Autowired
    private PrioridadRepository prioridadRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PrioridadMapper prioridadMapper;

    @Autowired
    private PrioridadCompletoMapper prioridadCompletoMapper;

    @Override
    public List<PrioridadDto> listarPrioridades() {
        List<PrioridadDto> prioridadDtos = new ArrayList<>();
        List<Prioridad> prioridades = prioridadRepository.findAll();
        if(!prioridades.isEmpty()){
            for (Prioridad prioridad: prioridades) {
                PrioridadDto prioridadDto = prioridadMapper.toDto(prioridad);
                prioridadDtos.add(prioridadDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("prioridadesNotFound",null, Locale.getDefault()));
        }
        return prioridadDtos;
    }

    @Override
    public void guardar(PrioridadDto prioridadDto) {
            Prioridad prioridad = prioridadMapper.toEntity(prioridadDto);
            prioridadRepository.save(prioridad);
    }

    @Override
    public void eliminar(int id_prioridad) {
        prioridadRepository.findById(id_prioridad).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("prioridadNotFound",null, Locale.getDefault()))
        );
        prioridadRepository.deleteById(id_prioridad);
    }

    @Override
    public PrioridadDto editarPrioridad(int id, PrioridadDto prioridadDto) {
        Prioridad prioridad = prioridadRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("prioridadNotFound",null, Locale.getDefault()))
        );
        prioridadMapper.updateEntity(prioridadDto,prioridad);
        prioridadRepository.save(prioridad);
        return prioridadMapper.toDto(prioridad);
    }

    @Override
    public PrioridadCompletoDto encontrarPrioridad(int id_prioridad) {
        return prioridadCompletoMapper.toDto(prioridadRepository.findById(id_prioridad).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("prioridadNotFound",null, Locale.getDefault()))
        ));
    }
}
