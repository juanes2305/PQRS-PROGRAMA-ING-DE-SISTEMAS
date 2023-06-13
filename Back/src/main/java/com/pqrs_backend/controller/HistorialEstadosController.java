package com.pqrs_backend.controller;

import com.pqrs_backend.dto.HistorialEstadosDto;
import com.pqrs_backend.entity.HistorialEstados;
import com.pqrs_backend.service.HistorialEstadosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/historialestados")
public class HistorialEstadosController {

    @Autowired
    private HistorialEstadosService historialEstadosService;

    private Map<String,Object> response = new HashMap<>();

    @PermitAll
    @GetMapping("/all")
    public ResponseEntity<?> listarHistorialEstados(){
        response.clear();
        response.put("historialEstados",historialEstadosService.listarHistorialEstados());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerHistorialEstado(@PathVariable int id) {
        response.clear();
        response.put("historialEstados", historialEstadosService.encontrarHistorialEstados(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarHistorialEstado(@Valid @RequestBody HistorialEstadosDto historialEstadosDto){
        response.clear();
        historialEstadosService.guardar(historialEstadosDto);
        response.put("message", "Historial de estado registrado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarHistorialEstado(@PathVariable int id, @RequestBody HistorialEstadosDto historialEstadosDto) {
        response.clear();
        HistorialEstadosDto historialEstadosActualizado = historialEstadosService.editarHistorialEstados(id, historialEstadosDto);
        response.put("message", "Historial de estado actualizado satisfactoriamente");
        response.put("historialEstados", historialEstadosActualizado);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarHistorialEstado(@PathVariable int id) {
        response.clear();
        historialEstadosService.eliminar(id);
        response.put("message", "Historial de estado eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/filtro/{id_estado}/{id_tipo}")
    public ResponseEntity<?> filtroHistorial(@PathVariable("id_estado") int idEstado, @PathVariable("id_tipo") int idTipo) {
        response.clear();
        response.put("historial", historialEstadosService.FiltroHistorial(idEstado, idTipo));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
