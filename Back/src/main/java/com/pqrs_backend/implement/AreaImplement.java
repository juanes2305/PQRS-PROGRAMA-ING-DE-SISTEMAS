package com.pqrs_backend.implement;

import com.pqrs_backend.dto.AreaCompletoDto;
import com.pqrs_backend.dto.AreaDto;
import com.pqrs_backend.entity.Area;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.AreaCompletoMapper;
import com.pqrs_backend.mapper.AreaMapper;
import com.pqrs_backend.repository.AreaRepository;
import com.pqrs_backend.service.AreaService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AreaImplement implements AreaService {

    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private AreaCompletoMapper areaCompletoMapper;
    @Override
    public List<AreaDto> listarAreas() {
        List<AreaDto> areaDtos = new ArrayList<>();
        List<Area> areas = areaRepository.findAll();
        if(!areas.isEmpty()){
            for (Area area: areas) {
                AreaDto areaDto = areaMapper.toDto(area);
                areaDtos.add(areaDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("areasNotFound",null, Locale.getDefault()));
        }
        return areaDtos;
    }

    @Override
    public void guardar(AreaDto areaDto) {
            Area area = areaMapper.toEntity(areaDto);
            areaRepository.save(area);
    }

    @Override
    public void eliminar(int id_area) {
        areaRepository.findById(id_area).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("areaNotFound",null, Locale.getDefault()))
        );
        areaRepository.deleteById(id_area);
    }

    @Override
    public AreaDto editarArea(int id, AreaDto areaDto) {
        Area area = areaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("areaNotFound",null, Locale.getDefault()))
        );
        areaMapper.updateEntity(areaDto,area);
        areaRepository.save(area);
        return areaMapper.toDto(area);
    }

    @Override
    public AreaCompletoDto encontrarArea(int id_area) {
        return areaCompletoMapper.toDto(areaRepository.findById(id_area).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("areaNotFound",null, Locale.getDefault()))
        ));
    }
}
