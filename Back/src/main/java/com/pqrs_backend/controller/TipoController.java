package com.pqrs_backend.controller;

import com.pqrs_backend.dto.TipoDto;
import com.pqrs_backend.entity.Tipo;
import com.pqrs_backend.service.TipoService;
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
@RequestMapping("/tipo")
public class TipoController {
    @Autowired
    private TipoService tipoService;

    private Map<String,Object> response = new HashMap<>();

    @PermitAll
    @GetMapping("/all")
    public ResponseEntity<?> listarTipos(){
        response.clear();
        response.put("tipo",tipoService.listarTipos());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTipo(@PathVariable int id) {
        response.clear();
        response.put("tipo", tipoService.encontrarTipo(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTipo(@Valid @RequestBody TipoDto tipo){
        response.clear();
        tipoService.guardar(tipo);
        response.put("message", "Tipo registrado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarTipo(@PathVariable int id, @RequestBody TipoDto tipo) {
        response.clear();
        TipoDto tipoActualizado = tipoService.editarTipo(id, tipo);
        response.put("message", "Tipo actualizado satisfactoriamente");
        response.put("tipo", tipoActualizado);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTipo(@PathVariable int id) {
        response.clear();
        tipoService.eliminar(id);
        response.put("message", "Tipo eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
