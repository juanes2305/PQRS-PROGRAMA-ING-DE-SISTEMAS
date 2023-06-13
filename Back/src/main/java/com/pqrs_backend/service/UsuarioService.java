package com.pqrs_backend.service;

import com.pqrs_backend.dto.UsuarioCompletoDto;
import com.pqrs_backend.dto.UsuarioDto;
import com.pqrs_backend.dto.UsuarioSinPasswordDto;
import com.pqrs_backend.dto.VerifyTokenRequestDto;
import com.pqrs_backend.entity.Usuario;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface UsuarioService {
    public List<UsuarioSinPasswordDto> listarUsuarios();

    public void guardar(UsuarioDto usuarioDto);

    public void eliminar(int id_usuario);

    public void desactivar(int id_usuario);

    public UsuarioCompletoDto encontrarUsuario(int id_usuario);

    public UsuarioDto editarUsuario(int id, UsuarioDto usuarioDto) throws IOException;

    public UsuarioDto loadImage(UsuarioDto usuarioDto) throws IOException;

    public UsuarioCompletoDto encontrarUsuarioByEmail(String email);
}
