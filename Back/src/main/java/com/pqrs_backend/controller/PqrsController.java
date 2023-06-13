package com.pqrs_backend.controller;

import com.pqrs_backend.dto.EstadoDto;
import com.pqrs_backend.dto.PqrsCompletoDto;
import com.pqrs_backend.dto.PqrsDto;
import com.pqrs_backend.dto.ReporteDto;
import com.pqrs_backend.entity.Usuario;
import com.pqrs_backend.repository.UsuarioRepository;
import com.pqrs_backend.service.EmailService;
import com.pqrs_backend.service.PqrsService;
import com.pqrs_backend.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/radicado")
public class PqrsController {
    @Autowired
    private PqrsService pqrsService;

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    private Map<String,Object> response = new HashMap<>();

    @PermitAll
    @GetMapping("/all")
    public ResponseEntity<?> listarPQRS(){
        response.clear();
        response.put("pqrs",pqrsService.listarPQRS());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/recientes")
    public ResponseEntity<?> listarUltimosRadicados(){
        response.clear();
        response.put("pqrs",pqrsService.listarUltimosPQRS());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPQRS(@PathVariable int id) {
        response.clear();
        response.put("pqrs", pqrsService.encontrarPQRS(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_TEACHER')")
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPQRS(@Valid @RequestBody PqrsDto pqrsDto) throws IOException {
        response.clear();
        pqrsService.loadImage(pqrsDto);
        pqrsService.guardar(pqrsDto);
        Usuario admin = usuarioRepository.findByRol(1);
        Usuario usuario = usuarioRepository.findById(pqrsDto.getUsuario().getId_usuario()).get();
        emailService.emailNuevaPregunta(admin.getEmail(),"Administrador",usuario.getNombre());
        response.put("message", "PQRS registrado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STUDENT') OR hasRole('ROLE_TEACHER')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarPQRS(@PathVariable int id, @RequestBody PqrsDto pqrsDto) throws IOException {
        response.clear();
        PqrsDto pqrsActualizado = pqrsService.editarPQRS(id, pqrsDto);
        response.put("message", "PQRS actualizado satisfactoriamente");
        response.put("pqrs", pqrsActualizado);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/cambiarEstado/{id}")
    public ResponseEntity<?> cambiarEstadoPqrs(@PathVariable int id, @RequestBody EstadoDto estadoDto) {
        response.clear();

        pqrsService.cambiarEstado(id, estadoDto);
        response.put("message", "Estado cambiado con éxito");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPQRS(@PathVariable int id) {
        response.clear();
        pqrsService.eliminar(id);
        response.put("message", "PQRS eliminado satisfactoriamente");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    //REPORTE DE PQRS'S
    @PermitAll
    @GetMapping( value = "/pdf/{id_tipo}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarPDF(@PathVariable int id_tipo) throws FileNotFoundException, JRException {

        List<PqrsCompletoDto> radicados = pqrsService.listarRadicados(id_tipo);

        Object[] arr = reporteService.llenarReporte(radicados);

        HashMap<String, Object> map = (HashMap<String, Object>) arr[0];

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource((List<ReporteDto>) arr[1]);

        JasperReport compileReport = JasperCompileManager.compileReport( getClass().getResourceAsStream("/Reporte.jrxml"));

        JasperPrint reporte = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);

        byte[] data = JasperExportManager.exportReportToPdf(reporte);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte_radicados.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }


}
