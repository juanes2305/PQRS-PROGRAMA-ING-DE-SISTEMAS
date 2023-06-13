package com.pqrs_backend.controller;

import com.pqrs_backend.entity.Queja;
import com.pqrs_backend.service.QuejaService;
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
@RequestMapping("/queja")
public class QuejaController {
    @Autowired
    private QuejaService quejaService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarQuejas(){
        response.clear();
        response.put("quejas",quejaService.listarQuejas());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerQueja(@PathVariable int id) {
        response.clear();
        response.put("queja", quejaService.encontrarQueja(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PermitAll
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarQueja(@Valid @RequestBody Queja queja){
        response.clear();
        quejaService.guardar(queja);
        response.put("message", "Queja registrada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarQueja(@PathVariable int id, @RequestBody Queja queja) {
        response.clear();
        Queja quejaActualizada = quejaService.editarQueja(id, queja);
        response.put("message", "Queja actualizada satisfactoriamente");
        response.put("queja", quejaActualizada);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarQueja(@PathVariable int id) {
        response.clear();
        quejaService.eliminar(id);
        response.put("message", "Queja eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
