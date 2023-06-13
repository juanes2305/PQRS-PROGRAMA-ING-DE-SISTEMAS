package com.pqrs_backend.implement;

import com.pqrs_backend.dto.TipoDto;
import com.pqrs_backend.dto.TipoCompletoDto;
import com.pqrs_backend.dto.TipoDto;
import com.pqrs_backend.entity.Tipo;
import com.pqrs_backend.entity.Tipo;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.TipoCompletoMapper;
import com.pqrs_backend.mapper.TipoMapper;
import com.pqrs_backend.repository.TipoRepository;
import com.pqrs_backend.service.TipoService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class TipoImplement implements TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private TipoMapper tipoMapper;

    @Autowired
    private TipoCompletoMapper tipoCompletoMapper;

    @Override
    public List<TipoDto> listarTipos() {

        List<TipoDto> tiposDto = new ArrayList<>();

        List<Tipo> tipos = tipoRepository.findAll();

        if(!tipos.isEmpty()){
            for (Tipo tipo: tipos) {
                TipoDto tipoDto = tipoMapper.toDto(tipo);
                tiposDto.add(tipoDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("tiposNotFound",null, Locale.getDefault()));
        }

        return tiposDto;
    }

    @Override
    public void guardar(TipoDto tipoDto) {
            Tipo tipo = tipoMapper.toEntity(tipoDto);
            tipoRepository.save(tipo);
    }

    @Override
    public void eliminar(int id_tipo) {
        tipoRepository.findById(id_tipo).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("tipoNotFound",null, Locale.getDefault()))
        );
        tipoRepository.deleteById(id_tipo);
    }

    @Override
    public TipoDto editarTipo(int id, TipoDto tipoDto) {
        Tipo tipo = tipoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("tipoNotFound",null, Locale.getDefault()))
        );
        tipoMapper.updateEntity(tipoDto,tipo);
        tipoRepository.save(tipo);
        return tipoMapper.toDto(tipo);
    }

    @Override
    public TipoCompletoDto encontrarTipo(int id_tipo) {
        return tipoCompletoMapper.toDto(tipoRepository.findById(id_tipo).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("tipoNotFound",null, Locale.getDefault()))
        ));
    }
}
