package com.pqrs_backend.controller;

import com.pqrs_backend.dto.EstadoDto;
import com.pqrs_backend.entity.Estado;
import com.pqrs_backend.service.EstadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarEstados(){
        response.clear();
        response.put("estados",estadoService.listarEstados());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEstado(@PathVariable int id) {
        response.clear();
        response.put("estado", estadoService.encontrarEstado(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEstado(@Valid @RequestBody EstadoDto estadoDto){
        response.clear();
        estadoService.guardar(estadoDto);
        response.put("message", "Estado registrado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarEstado(@PathVariable int id, @RequestBody EstadoDto estadoDto) {
        response.clear();
        EstadoDto estadoActualizado = estadoService.editarEstado(id, estadoDto);
        response.put("message", "Estado actualizado satisfactoriamente");
        response.put("estado", estadoActualizado);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEstado(@PathVariable int id) {
        response.clear();
        estadoService.eliminar(id);
        response.put("message", "Estado eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
