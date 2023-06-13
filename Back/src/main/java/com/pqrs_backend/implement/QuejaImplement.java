package com.pqrs_backend.implement;

import com.pqrs_backend.entity.Queja;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.repository.QuejaRepository;
import com.pqrs_backend.service.QuejaService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class QuejaImplement implements QuejaService {

    @Autowired
    private QuejaRepository quejaRepository;

    @Autowired
    private MessageUtil messageUtil;
    @Override
    public List<Queja> listarQuejas() {
        return quejaRepository.findAll();
    }

    @Override
    public void guardar(Queja queja) {
            quejaRepository.save(queja);
    }

    @Override
    public void eliminar(int id_queja) {
        quejaRepository.findById(id_queja).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("quejaNotFound",null, Locale.getDefault()))
        );
        quejaRepository.deleteById(id_queja);
    }

    @Override
    public Queja editarQueja(int id, Queja queja) {
        Queja queja1 = quejaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("quejaNotFound",null, Locale.getDefault()))
        );
        queja1.setId_queja(queja.getId_queja());
        if(queja.getNombre_usuario()!=null)
            queja1.setNombre_usuario(queja.getNombre_usuario());
        if(queja.getDescripcion()!=null)
            queja1.setDescripcion(queja.getDescripcion());
        quejaRepository.save(queja1);
        return queja1;
    }

    @Override
    public Queja encontrarQueja(int id_queja) {
        return quejaRepository.findById(id_queja).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("quejaNotFound",null, Locale.getDefault())
        ));
    }
}
