package com.pqrs_backend.implement;

import com.pqrs_backend.dto.EstadoDto;
import com.pqrs_backend.dto.PqrsCompletoDto;
import com.pqrs_backend.dto.PqrsDto;
import com.pqrs_backend.dto.UsuarioDto;
import com.pqrs_backend.entity.*;
import com.pqrs_backend.entity.Pqrs;
import com.pqrs_backend.exception.NotFoundException;
import com.pqrs_backend.mapper.PqrsCompletoMapper;
import com.pqrs_backend.mapper.PqrsMapper;
import com.pqrs_backend.repository.*;
import com.pqrs_backend.repository.PqrsRepository;
import com.pqrs_backend.service.PqrsService;
import com.pqrs_backend.service.StorageService;
import com.pqrs_backend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

@Service
public class PqrsImplement implements PqrsService {


    @Autowired
    private PqrsRepository pqrsRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PrioridadRepository prioridadRepository;

    @Autowired
    private HistorialEstadosRepository historialEstadosRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PqrsMapper pqrsMapper;

    @Autowired
    private PqrsCompletoMapper pqrsCompletoMapper;

    @Autowired
    private StorageService service;


    @Override
    public List<PqrsDto> listarPQRS() {
        List<PqrsDto> pqrsDtos = new ArrayList<>();
        List<Pqrs> pqrsList = pqrsRepository.findAll();

        if(!pqrsList.isEmpty()) {
            for (Pqrs pqrs : pqrsList) {
                PqrsDto pqrsDto = pqrsMapper.toDto(pqrs);
                pqrsDtos.add(pqrsDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("pqrsEmpty",null, Locale.getDefault()));
        }
        return pqrsDtos;
    }

    @Override
    public List<PqrsDto> listarUltimosPQRS() {
        List<PqrsDto> pqrsDtos = new ArrayList<>();
        List<Pqrs> pqrsList = pqrsRepository.encontrarUltimosRadicados();

        if(!pqrsList.isEmpty()) {
            for (Pqrs pqrs : pqrsList) {
                PqrsDto pqrsDto = pqrsMapper.toDto(pqrs);
                pqrsDtos.add(pqrsDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("pqrsEmpty",null, Locale.getDefault()));
        }
        return pqrsDtos;
    }

    @Override
    public List<PqrsCompletoDto> listarRadicados(int id_tipo) {
        List<PqrsCompletoDto> radicados = new ArrayList<>();
        List<Pqrs> pqrsList = pqrsRepository.findAllByTipo(id_tipo);

        if(!pqrsList.isEmpty()) {
            for (Pqrs pqrs : pqrsList) {
                PqrsCompletoDto pqrsCompletoDto = pqrsCompletoMapper.toDto(pqrs);
                radicados.add(pqrsCompletoDto);
            }
        }else{
            throw new NotFoundException(messageUtil.getMessage("pqrsEmpty",null, Locale.getDefault()));
        }
        return radicados;
    }

    @Transactional
    @Override
    public void guardar(PqrsDto pqrsDto) {
            Pqrs pqrs = pqrsMapper.toEntity(pqrsDto);
            pqrsRepository.save(pqrs);
            Estado estado = estadoRepository.findById(2).get(); //Sería estado : pendiente
            HistorialEstados historialEstado = new HistorialEstados(0, LocalDateTime.now(),pqrs,estado);
            historialEstadosRepository.save(historialEstado);
    }

    @Override
    public void eliminar(int id_radicado) {
        pqrsRepository.findById(id_radicado).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pqrsNotFound",null, Locale.getDefault()))
        );

        pqrsRepository.deleteById(id_radicado);
    }

    @Transactional
    @Override
    public PqrsDto editarPQRS(int id, PqrsDto pqrsDto) throws IOException {
        Pqrs pqrs = pqrsRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pqrsNotFound",null, Locale.getDefault()))
        );

        if (pqrsDto.getTitulo() != null)
            pqrs.setTitulo(pqrsDto.getTitulo());

        if (pqrsDto.getDescripcion() != null)
            pqrs.setDescripcion(pqrsDto.getDescripcion());

        if (!pqrsDto.getAnexo().equals("")){
            PqrsDto pqrsDto1 = loadImage(pqrsDto);
            pqrs.setAnexo(pqrsDto1.getAnexo());
        }



        Usuario usuario = usuarioRepository.findById(pqrsDto.getUsuario().getId_usuario()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("usuarioNotFound", null, Locale.getDefault()))
        );
        if(pqrsDto.getUsuario() != null){
            if(usuario.getRol().getId_rol() == 2 && usuario.getRol().getDescripcion().equalsIgnoreCase("STUDENT")){
                pqrs.setUsuario(usuario);
            }else{
                throw new NotFoundException(messageUtil.getMessage("usuarioDoesntExist",null, Locale.getDefault()));
            }
        }

        Prioridad prioridad = prioridadRepository.findById(pqrsDto.getPrioridad().getId_prioridad()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("prioridadNotFound", null, Locale.getDefault()))
        );
        if (pqrsDto.getPrioridad() != null)
            pqrs.setPrioridad(prioridad);

        Area area = areaRepository.findById(pqrsDto.getArea().getId_area()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("areaNotFound", null, Locale.getDefault()))
        );
        if (pqrsDto.getArea() != null)
            pqrs.setArea(area);

        Tipo tipo = tipoRepository.findById(pqrsDto.getTipo().getId_tipo()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("tipoNotFound", null, Locale.getDefault()))
        );
        if (pqrsDto.getTipo() != null)
            pqrs.setTipo(tipo);


        pqrsRepository.save(pqrs);

        return pqrsMapper.toDto(pqrs);
    }

    @Override
    public void cambiarEstado(int id, EstadoDto estadoDto) {
        Pqrs pqrs = pqrsRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pqrsNotFound",null, Locale.getDefault()))
        );


        Estado estado = estadoRepository.findById(estadoDto.getId_estado()).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("estadoNotFound",null, Locale.getDefault()))
        );

        HistorialEstados historialEstado = new HistorialEstados();
        historialEstado.setPqrs(pqrs);
        historialEstado.setEstado(estado);
        historialEstado.setFecha_cambio(LocalDateTime.now());

        historialEstadosRepository.save(historialEstado);

    }

    @Override
    public PqrsCompletoDto encontrarPQRS(int id_radicado) {
        return pqrsCompletoMapper.toDto(pqrsRepository.findById(id_radicado).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("pqrsNotFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public String encontrarEstado(int id_radicado) {
        return pqrsRepository.encontrarEstado(id_radicado);
    }

    @Override
    public PqrsDto loadImage(PqrsDto pqrsDto) throws IOException {
        if (!pqrsDto.getAnexo().equals("")) {
            if (pqrsRepository.findById(pqrsDto.getId_radicado()).isPresent()) {
                Pqrs pqrs = pqrsRepository.findById(pqrsDto.getId_radicado()).get();
                if (!pqrs.getAnexo().equals(pqrsDto.getAnexo())) {
                    saveDocument(pqrsDto);
                }
            }else{
                saveDocument(pqrsDto);
            }
        }
        return pqrsDto;
    }

    public void saveDocument(PqrsDto pqrsDto) throws IOException {
        String[] anexo = pqrsDto.getAnexo().split("\\s+"); //Split por espaciado al String con el archivo encriptado. ejemplo: 76sdasd7sads jpg nombrearchivo
        byte[] image1 = Base64.getMimeDecoder().decode(anexo[0]); //Se desencripta la imagen
        File file = convertBytesToFile(image1, anexo[1]); //Se pasa a tipo File
        String[] tipo = anexo[1].split("\\."); //El tipo del archivo
        String nombre = anexo[2] + "." + tipo[tipo.length - 1];
        if (file != null) {
            pqrsDto.setAnexo(nombre);
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
