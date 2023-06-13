package com.pqrs_backend.implement;

import com.pqrs_backend.dto.PqrsCompletoDto;
import com.pqrs_backend.dto.ReporteDto;
import com.pqrs_backend.service.EstadoService;
import com.pqrs_backend.service.PqrsService;
import com.pqrs_backend.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ReporteImplement implements ReporteService {

    @Autowired
    private PqrsService pqrsService;

    @Override
    public Object[] llenarReporte(List<PqrsCompletoDto> radicados) {

        HashMap<String, Object> map = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        String fecha = dateFormat.format(new Date());

        map.put("fecha", fecha);
        map.put("cantidad", radicados.size());

        Object[] arr = new Object[2];
        arr[0] = map;

        List<ReporteDto> reporte = new ArrayList<>();

        String area, tipo, prioridad, estado;
        for (int i = 0; i < radicados.size(); i++) {
            area = radicados.get(i).getArea().getNombre();
            tipo = radicados.get(i).getTipo().getNombre();
            prioridad = radicados.get(i).getPrioridad().getDescripcion();
            estado = pqrsService.encontrarEstado(radicados.get(i).getId_radicado());

            if(estado == null) {
                estado = "No tiene";
            }

            reporte.add(new ReporteDto(radicados.get(i).getId_radicado(), radicados.get(i).getTitulo(), area, tipo, prioridad, estado));
        }

        arr[1] = reporte;

        return arr;
    }
}
