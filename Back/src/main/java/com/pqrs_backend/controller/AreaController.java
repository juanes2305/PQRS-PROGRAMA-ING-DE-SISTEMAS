package com.pqrs_backend.controller;

import com.pqrs_backend.dto.AreaDto;
import com.pqrs_backend.service.AreaService;
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
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    private Map<String,Object> response = new HashMap<>();

    @PermitAll
    @GetMapping("/all")
    public ResponseEntity<?> listarAreas(){
        response.clear();
        response.put("areas",areaService.listarAreas());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerArea(@PathVariable int id) {
        response.clear();
        response.put("area", areaService.encontrarArea(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarArea(@Valid @RequestBody AreaDto areaDto){
        response.clear();
        areaService.guardar(areaDto);
        response.put("message", "Area registrada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarArea(@PathVariable int id, @RequestBody AreaDto areaDto) {
        response.clear();
        AreaDto areaActualizada = areaService.editarArea(id, areaDto);
        response.put("message", "Area actualizada satisfactoriamente");
        response.put("area", areaActualizada);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarArea(@PathVariable int id) {
        response.clear();
        areaService.eliminar(id);
        response.put("message", "Area eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
