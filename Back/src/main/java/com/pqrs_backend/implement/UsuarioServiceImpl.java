package com.pqrs_backend.implement;


import com.pqrs_backend.dto.UsuarioCompletoDto;
import com.pqrs_backend.dto.UsuarioDto;
import com.pqrs_backend.dto.UsuarioSinPasswordDto;
import com.pqrs_backend.entity.Rol;
import com.pqrs_backend.entity.Usuario;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.UserDetailsMapper;
import com.pqrs_backend.mapper.UsuarioCompletoMapper;
import com.pqrs_backend.mapper.UsuarioMapper;
import com.pqrs_backend.mapper.UsuarioSinPasswordMapper;
import com.pqrs_backend.repository.RolRepository;
import com.pqrs_backend.repository.UsuarioRepository;
import com.pqrs_backend.service.StorageService;
import com.pqrs_backend.service.UsuarioService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

@Service("userDetailsService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	@Autowired
	private UsuarioMapper usuarioMapper;

	@Autowired
	private UsuarioCompletoMapper usuarioCompletoMapper;

	@Autowired
	private UsuarioSinPasswordMapper usuarioSinPasswordMapper;

	@Autowired
	private StorageService service;


	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository userRepository, RolRepository roleRepository) {
		this.usuarioRepository = userRepository;
		this.rolRepository = roleRepository;
	}
	@Override
	public List<UsuarioSinPasswordDto> listarUsuarios() {
		List<UsuarioSinPasswordDto> usuarioSinPasswordDtos = new ArrayList<>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		if(!usuarios.isEmpty()){
			for (Usuario usuario: usuarios) {
				UsuarioSinPasswordDto usuarioSinPasswordDto = usuarioSinPasswordMapper.toDto(usuario);
				usuarioSinPasswordDtos.add(usuarioSinPasswordDto);
			}
		} else {
			throw new NotFoundException(messageUtil.getMessage("usuariosNotFound",null, Locale.getDefault()));
		}
		return usuarioSinPasswordDtos;
	}

	@Override
	public void guardar(UsuarioDto usuarioDto) {
		Usuario usuario = usuarioMapper.toEntity(usuarioDto);
		usuarioRepository.save(usuario);
	}

	@Override
	public void eliminar(int id_usuario) {
		usuarioRepository.findById(id_usuario).orElseThrow(
				() -> new NotFoundException(messageUtil.getMessage("usuarioNotFound",null, Locale.getDefault()))
		);
		usuarioRepository.deleteById(id_usuario);
	}

	@Override
	public void desactivar(int id_usuario) {

	}

	@Override
	public UsuarioCompletoDto encontrarUsuario(int id_usuario) {
		return usuarioCompletoMapper.toDto(usuarioRepository.findById(id_usuario).orElseThrow(
				() -> new NotFoundException(messageUtil.getMessage("usuarioNotFound",null, Locale.getDefault()))
		));
	}

	@Override
	public UsuarioDto editarUsuario(int id, UsuarioDto usuarioDto) throws IOException {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new NotFoundException(messageUtil.getMessage("UsuarioNotFound",null, Locale.getDefault()))
		);
		usuario.setId_usuario(usuarioDto.getId_usuario());
		if(usuarioDto.getNombre()!=null)
			usuario.setNombre(usuarioDto.getNombre());
		if(usuarioDto.getApellido()!=null)
			usuario.setApellido(usuarioDto.getApellido());
		if(usuarioDto.getTelefono()!=null)
			usuario.setTelefono(usuarioDto.getTelefono());
		if(usuarioDto.getEmail()!=null)
			usuario.setEmail(usuarioDto.getEmail());
		if(usuarioDto.getPassword()!=null)
			usuario.setPassword(usuarioDto.getPassword());
		if(!usuarioDto.getImagen().equals("")){
			UsuarioDto usuarioDto1 = loadImage(usuarioDto);
			usuario.setImagen(usuarioDto1.getImagen());
		}

		Rol rol = rolRepository.findById(usuarioDto.getRol().getId_rol()).orElseThrow(
				() -> new NotFoundException(messageUtil.getMessage("rolNotFound",null, Locale.getDefault()))
		);
		if(usuarioDto.getRol()!=null)
			usuario.setRol(rol);
		usuarioRepository.save(usuario);
		return usuarioMapper.toDto(usuario);
	}

	@Override
	public UsuarioDto loadImage(UsuarioDto usuarioDto) throws IOException {
		if (!usuarioDto.getImagen().equals("")) {
			if (usuarioRepository.findById(usuarioDto.getId_usuario()).isPresent()) {
				Usuario cliente = usuarioRepository.findById(usuarioDto.getId_usuario()).get();
				if (!cliente.getImagen().equals(usuarioDto.getImagen())) {
					saveDocument(usuarioDto);
				}
			}else{
				saveDocument(usuarioDto);
			}
		}
		return usuarioDto;
	}

	@Override
	public UsuarioCompletoDto encontrarUsuarioByEmail(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email).get();
		if(usuario!=null){
			UsuarioCompletoDto usuarioCompletoDto = usuarioCompletoMapper.toDto(usuario);
			return usuarioCompletoDto;
		}else{
			throw new RuntimeException("No existe el usuario con ese correo.");
		}
	}

	public void saveDocument(UsuarioDto usuarioDto) throws IOException {
		String[] anexo = usuarioDto.getImagen().split("\\s+"); //Split por espaciado al String con el archivo encriptado. ejemplo: 76sdasd7sads jpg nombrearchivo
		byte[] image1 = Base64.getMimeDecoder().decode(anexo[0]); //Se desencripta la imagen
		File file = convertBytesToFile(image1, anexo[1]); //Se pasa a tipo File
		String[] tipo = anexo[1].split("\\."); //El tipo del archivo
		String nombre = anexo[2] + "." + tipo[tipo.length - 1];
		if (file != null) {
			usuarioDto.setImagen(nombre);
			service.uploadFile(file, nombre);
		}
		file.delete();
	}

	public File convertBytesToFile(byte[] bytes, String filename) throws IOException {
		File file = new File(filename);
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(bytes);
		outputStream.close();
		return file;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Usuario retrievedUser = usuarioRepository.findByEmail(username).get();
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return UserDetailsMapper.build(retrievedUser);
	}
}