package com.pqrs_backend.service;

import com.pqrs_backend.entity.Queja;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuejaService {
    public List<Queja> listarQuejas();

    public void guardar(Queja queja);

    public void eliminar(int id_queja);

    public Queja editarQueja(int id, Queja queja);

    public Queja encontrarQueja(int id_queja);
}
