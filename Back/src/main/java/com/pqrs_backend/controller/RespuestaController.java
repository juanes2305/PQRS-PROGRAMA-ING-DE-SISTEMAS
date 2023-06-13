package com.pqrs_backend.controller;

import com.pqrs_backend.dto.RespuestaDto;
import com.pqrs_backend.entity.Pqrs;
import com.pqrs_backend.entity.Respuesta;
import com.pqrs_backend.entity.Usuario;
import com.pqrs_backend.repository.PqrsRepository;
import com.pqrs_backend.repository.UsuarioRepository;
import com.pqrs_backend.service.EmailService;
import com.pqrs_backend.service.RespuestaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;

    @Autowired
    private PqrsRepository pqrsRepository;

    @Autowired
    private EmailService emailService;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarRespuestas(){
        response.clear();
        response.put("respuestas",respuestaService.listarRespuestas());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRespuesta(@PathVariable int id) {
        response.clear();
        response.put("respuesta", respuestaService.encontrarRespuesta(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRespuesta(@Valid @RequestBody RespuestaDto respuestaDto) throws IOException {
        response.clear();
        respuestaService.loadImage(respuestaDto);
        respuestaService.guardar(respuestaDto);
        Pqrs radicado = pqrsRepository.findById(respuestaDto.getPqrs().getId_radicado()).get();
        emailService.emailRespuesta(radicado.getUsuario().getEmail(),radicado.getUsuario().getNombre(),radicado.getTitulo());
        response.put("message", "Respuesta registrada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarRespuesta(@PathVariable int id, @RequestBody RespuestaDto respuestaDto) {
        response.clear();
        RespuestaDto respuestaActualizada = respuestaService.editarRespuesta(id, respuestaDto);
        response.put("message", "Respuesta actualizada satisfactoriamente");
        response.put("respuesta", respuestaActualizada);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarRespuesta(@PathVariable int id) {
        response.clear();
        respuestaService.eliminar(id);
        response.put("message", "Respuesta eliminada satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_TEACHER')")
    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<?> respuestasByUsuario(@PathVariable int id_usuario){
        response.clear();
        response.put("respuestas",respuestaService.encontrarRespuestasUsuario(id_usuario));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_TEACHER')")
    @GetMapping("/radicado/{id_radicado}")
    public ResponseEntity<?> respuestaPorRadicado(@PathVariable int id_radicado){
        response.clear();
        response.put("respuesta",respuestaService.respuestaPorRadicado(id_radicado));
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
