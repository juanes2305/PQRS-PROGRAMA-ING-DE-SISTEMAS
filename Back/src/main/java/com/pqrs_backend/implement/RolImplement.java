package com.pqrs_backend.implement;

import com.pqrs_backend.dto.RolCompletoDto;
import com.pqrs_backend.dto.RolDto;
import com.pqrs_backend.entity.Rol;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.RolCompletoMapper;
import com.pqrs_backend.mapper.RolMapper;
import com.pqrs_backend.repository.RolRepository;
import com.pqrs_backend.service.RolService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RolImplement implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private RolMapper rolMapper;

    @Autowired
    private RolCompletoMapper rolCompletoMapper;


    @Override
    public List<RolDto> listarRoles() {
        List<RolDto> rolesDtos = new ArrayList<>();
        List<Rol> roles = rolRepository.findAll();
        if(!roles.isEmpty()){
            for (Rol rol: roles) {
                RolDto rolDto = rolMapper.toDto(rol);
                rolesDtos.add(rolDto);
            }
        } else {
            throw new NotFoundException(messageUtil.getMessage("rolesNotFound",null, Locale.getDefault()));
        }
        return rolesDtos;
    }

    @Override
    public void guardar(RolDto rolDto) {
            Rol rol = rolMapper.toEntity(rolDto);
            rolRepository.save(rol);
    }

    @Override
    public void eliminar(int id_rol) {
        rolRepository.findById(id_rol).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        rolRepository.deleteById(id_rol);
    }

    @Override
    public RolDto editarRol(int id, RolDto rolDto) {
        Rol rol = rolRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        );
        rolMapper.updateEntity(rolDto,rol);
        rolRepository.save(rol);
        return rolMapper.toDto(rol);
    }

    @Override
    public RolCompletoDto encontrarRol(int id_rol) {
        return rolCompletoMapper.toDto(rolRepository.findById(id_rol).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
        ));
    }
}
