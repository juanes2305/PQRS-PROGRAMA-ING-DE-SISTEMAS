package com.pqrs_backend.implement;

import com.pqrs_backend.entity.FQA;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.repository.FQARepository;
import com.pqrs_backend.service.FqaService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class FqaImplement implements FqaService {

    @Autowired
    private FQARepository fqaRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public List<FQA> listarFQAs() {
        return fqaRepository.findAll();
    }

    @Override
    public void guardar(FQA fqa) {
        fqaRepository.save(fqa);
    }

    @Override
    public void eliminar(int id_fqa) {
        fqaRepository.findById(id_fqa).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("fqaNotFound",null, Locale.getDefault()))
        );
        fqaRepository.deleteById(id_fqa);
    }

    @Override
    public FQA editarFQA(int id, FQA fqa) {

        FQA fqa1 = fqaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("fqaNotFound",null, Locale.getDefault()))
        );

        fqa1.setId_fqa(fqa.getId_fqa());

        if(fqa.getTitulo()!=null)
            fqa1.setTitulo(fqa.getTitulo());

        if(fqa.getRespuesta()!=null)
            fqa1.setRespuesta(fqa.getRespuesta());

        fqaRepository.save(fqa1);
        return fqa1;
    }

    @Override
    public FQA encontrarFQA(int id_fqa) {
        return fqaRepository.findById(id_fqa).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("fqaNotFound",null, Locale.getDefault())
        ));
    }
}
