package com.pqrs_backend.controller;

import com.pqrs_backend.entity.FQA;
import com.pqrs_backend.service.FqaService;
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
@RequestMapping("/fqa")
public class FQAController {
    @Autowired
    private FqaService fqaService;

    private Map<String,Object> response = new HashMap<>();

    @PermitAll
    @GetMapping("/all")
    public ResponseEntity<?> listarFqas(){
        response.clear();
        response.put("fqas",fqaService.listarFQAs());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerFqa(@PathVariable int id) {
        response.clear();
        response.put("fqa", fqaService.encontrarFQA(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarFqa(@Valid @RequestBody FQA fqa){
        response.clear();
        fqaService.guardar(fqa);
        response.put("message", "Fqa registrada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarFqa(@PathVariable int id, @RequestBody FQA fqa) {
        response.clear();
        FQA fqaActualizada = fqaService.editarFQA(id, fqa);
        response.put("message", "Fqa actualizada satisfactoriamente");
        response.put("fqa", fqaActualizada);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarFqa(@PathVariable int id) {
        response.clear();
        fqaService.eliminar(id);
        response.put("message", "Fqa eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
