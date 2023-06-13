package com.pqrs_backend.service;

import com.pqrs_backend.dto.PqrsCompletoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReporteService {

    public Object[] llenarReporte(List<PqrsCompletoDto> radicados);
}
