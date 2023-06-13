package com.pqrs_backend.implement;

import com.pqrs_backend.dto.RespuestaDto;
import com.pqrs_backend.dto.UsuarioDto;
import com.pqrs_backend.entity.*;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.RespuestaMapper;
import com.pqrs_backend.repository.*;
import com.pqrs_backend.service.RespuestaService;
import com.pqrs_backend.service.StorageService;
import com.pqrs_backend.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class RespuestaImplement implements RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private HistorialEstadosRepository historialEstadosRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PqrsRepository pqrsRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private StorageService service;

    @Autowired
    private RespuestaMapper respuestaMapper;

    @Override
    public List<RespuestaDto> listarRespuestas() {
        List<RespuestaDto> respuestasDto = new ArrayList<>();

        List<Respuesta> respuestas = respuestaRepository.findAll();

        if(!respuestas.isEmpty()) {
            for (Respuesta respuesta : respuestas) {
                RespuestaDto respuestaDto = respuestaMapper.toDto(respuesta);
                respuestasDto.add(respuestaDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("respuestasEmpty",null, Locale.getDefault()));
        }
        return respuestasDto;
    }

    @Override
    public void guardar(RespuestaDto respuestaDto) {

        Respuesta respuesta = respuestaMapper.toEntity(respuestaDto);
        log.info(respuestaDto.getUsuario().getId_usuario()+"");
        pqrsRepository.findById(respuestaDto.getPqrs().getId_radicado()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pqrsNotFound", null, Locale.getDefault()))
        );
        respuestaRepository.save(respuesta);
        Pqrs pqrs = pqrsRepository.findById(respuestaDto.getPqrs().getId_radicado()).get();
        Estado estado = estadoRepository.findById(1).get();
        HistorialEstados historialEstado = new HistorialEstados(0, LocalDateTime.now(),pqrs,estado);
        historialEstadosRepository.save(historialEstado);

    }

    @Override
    public void eliminar(int id_respuesta) {
        respuestaRepository.findById(id_respuesta).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("respuestaNotFound",null, Locale.getDefault()))
        );
        respuestaRepository.deleteById(id_respuesta);
    }

    @Override
    public RespuestaDto editarRespuesta(int id, RespuestaDto respuestaDto) {
        Respuesta respuesta = respuestaRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("respuestaNotFound",null, Locale.getDefault()))
        );

        if (respuestaDto.getRespuesta() != null)
            respuesta.setRespuesta(respuestaDto.getRespuesta());

        if (respuestaDto.getCalificacion() > -1)
            respuesta.setCalificacion(respuestaDto.getCalificacion());

        if (!respuestaDto.getAnexo().equals(""))
            respuesta.setAnexo(respuestaDto.getAnexo());


        Usuario usuario = usuarioRepository.findById(respuestaDto.getUsuario().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("usuarioNotFound", null, Locale.getDefault()))
        );
        if(respuestaDto.getUsuario() != null){
            if(usuario.getRol().getId_rol() == 2 && usuario.getRol().getDescripcion().equalsIgnoreCase("STUDENT")){
                respuesta.setUsuario(usuario);
            }else{
                throw new NotFoundException(messageUtil.getMessage("usuarioDoesntExist",null, Locale.getDefault()));
            }
        }

        Pqrs pqrs = pqrsRepository.findById(respuestaDto.getPqrs().getId_radicado()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pqrsNotFound", null, Locale.getDefault()))
        );
        if (respuestaDto.getPqrs() != null)
            respuesta.setPqrs(pqrs);


        respuestaRepository.save(respuesta);

        return respuestaMapper.toDto(respuesta);
    }

    @Override
    public RespuestaDto encontrarRespuesta(int id_respuesta) {
        return respuestaMapper.toDto(respuestaRepository.findById(id_respuesta).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("respuestaNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public List<RespuestaDto> encontrarRespuestasUsuario(int id_usuario) {
        List<RespuestaDto> respuestaDtos = new ArrayList<>();
        List<Respuesta> respuestas = respuestaRepository.findRespuestasByIdUsuario(id_usuario);
        for (Respuesta respuesta: respuestas
             ) {
            RespuestaDto respuestaDto = respuestaMapper.toDto(respuesta);
            respuestaDtos.add(respuestaDto);

        }
        return respuestaDtos;
    }

    @Override
    public RespuestaDto loadImage(RespuestaDto respuestaDto) throws IOException {
        if (!respuestaDto.getAnexo().equals("")) {
            if (respuestaRepository.findById(respuestaDto.getId_respuesta()).isPresent()) {
                Respuesta cliente = respuestaRepository.findById(respuestaDto.getId_respuesta()).get();
                if (!cliente.getAnexo().equals(respuestaDto.getAnexo())) {
                    saveDocument(respuestaDto);
                }
            }else{
                saveDocument(respuestaDto);
            }
        }
        return respuestaDto;
    }

    @Override
    public RespuestaDto respuestaPorRadicado(int id_radicado) {
        Respuesta respuesta = respuestaRepository.findRespuestaByRadicado(id_radicado);
        RespuestaDto respuestaDto = respuestaMapper.toDto(respuesta);
        return respuestaDto;
    }

    public void saveDocument(RespuestaDto respuestaDto) throws IOException {
        String[] anexo = respuestaDto.getAnexo().split("\\s+"); //Split por espaciado al String con el archivo encriptado. ejemplo: 76sdasd7sads jpg nombrearchivo
        byte[] image1 = Base64.getMimeDecoder().decode(anexo[0]); //Se desencripta la imagen
        File file = convertBytesToFile(image1, anexo[1]); //Se pasa a tipo File
        String[] tipo = anexo[1].split("\\."); //El tipo del archivo
        String nombre = anexo[2] + "." + tipo[tipo.length - 1];
        if (file != null) {
            respuestaDto.setAnexo(nombre);
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
}
