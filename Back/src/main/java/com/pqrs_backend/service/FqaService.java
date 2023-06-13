package com.pqrs_backend.service;

import com.pqrs_backend.entity.FQA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FqaService {
    public List<FQA> listarFQAs();

    public void guardar(FQA fqa);

    public void eliminar(int id_fqa);

    public FQA editarFQA(int id, FQA fqa);

    public FQA encontrarFQA(int id_fqa);
}
