package com.pqrs_backend.controller;

import com.pqrs_backend.dto.PrioridadDto;
import com.pqrs_backend.entity.Prioridad;
import com.pqrs_backend.service.PrioridadService;
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
@RequestMapping("/prioridad")
public class PrioridadController {
    @Autowired
    private PrioridadService prioridadService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarPrioridads(){
        response.clear();
        response.put("prioridades",prioridadService.listarPrioridades());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPrioridad(@PathVariable int id) {
        response.clear();
        response.put("prioridad", prioridadService.encontrarPrioridad(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPrioridad(@Valid @RequestBody PrioridadDto prioridadDto){
        response.clear();
        prioridadService.guardar(prioridadDto);
        response.put("message", "Prioridad registrada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPrioridad(@PathVariable int id, @RequestBody PrioridadDto prioridadDto) {
        response.clear();
        PrioridadDto prioridadActualizada = prioridadService.editarPrioridad(id, prioridadDto);
        response.put("message", "Prioridad actualizada satisfactoriamente");
        response.put("prioridad", prioridadActualizada);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPrioridad(@PathVariable int id) {
        response.clear();
        prioridadService.eliminar(id);
        response.put("message", "Prioridad eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
