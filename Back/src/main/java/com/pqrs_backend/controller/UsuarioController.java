package com.pqrs_backend.controller;

import com.pqrs_backend.dto.UsuarioDto;
import com.pqrs_backend.entity.Usuario;
import com.pqrs_backend.service.EmailService;
import com.pqrs_backend.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/usuario") //http://localhost:8080/usuario/guardar
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String,Object> response = new HashMap<>();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listarUsuarios(){
        response.clear();
        response.put("usuario",usuarioService.listarUsuarios());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id) {
        response.clear();
        response.put("usuario", usuarioService.encontrarUsuario(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PermitAll
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) throws IOException {
        response.clear();
        usuarioService.loadImage(usuarioDto);
        usuarioDto.setPassword(bCryptPasswordEncoder.encode(usuarioDto.getPassword()));
        usuarioService.guardar(usuarioDto);
        emailService.emailRegistro(usuarioDto.getEmail(),usuarioDto.getNombre());
        response.put("message", "Usuario registrado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_TEACHER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable int id, @RequestBody UsuarioDto usuarioDto) throws IOException {
        response.clear();
        UsuarioDto usuarioActualizado = usuarioService.editarUsuario(id, usuarioDto);
        response.put("message", "Usuario actualizado satisfactoriamente");
        response.put("usuario", usuarioActualizado);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        response.clear();
        usuarioService.eliminar(id);
        response.put("message", "Usuario eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_TEACHER')")
    @GetMapping("/encontrar/{email}")
    public ResponseEntity<?> encontrarUsuarioByEmail(@PathVariable String email){
        response.clear();
        response.put("usuario", usuarioService.encontrarUsuarioByEmail(email));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
