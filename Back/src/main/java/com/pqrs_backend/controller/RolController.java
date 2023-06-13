package com.pqrs_backend.controller;

import com.pqrs_backend.dto.RolDto;
import com.pqrs_backend.entity.Rol;
import com.pqrs_backend.service.RolService;
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
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarRoles(){
        response.clear();
        response.put("roles",rolService.listarRoles());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRol(@PathVariable int id) {
        response.clear();
        response.put("rol", rolService.encontrarRol(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRol(@Valid @RequestBody RolDto rolDto){
        response.clear();
        rolService.guardar(rolDto);
        response.put("message", "Rol registrado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarRol(@PathVariable int id, @RequestBody RolDto rolDto) {
        response.clear();
        RolDto rolActualizado = rolService.editarRol(id, rolDto);
        response.put("message", "Rol actualizado satisfactoriamente");
        response.put("rol", rolActualizado);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable int id) {
        response.clear();
        rolService.eliminar(id);
        response.put("message", "Rol eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
