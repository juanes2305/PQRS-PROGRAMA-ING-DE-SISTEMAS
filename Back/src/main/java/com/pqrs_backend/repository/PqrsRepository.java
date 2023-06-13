package com.pqrs_backend.repository;

import com.pqrs_backend.entity.HistorialEstados;
import com.pqrs_backend.entity.Pqrs;
import com.pqrs_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PqrsRepository extends JpaRepository<Pqrs, Integer> {

    @Query(
            value = "SELECT e.titulo AS estado_actual\n" +
                    "FROM estado e\n" +
                    "INNER JOIN historial_estados he ON e.id_estado = he.id_estado\n" +
                    "WHERE he.fecha_cambio = (\n" +
                    "  SELECT MAX(fecha_cambio)\n" +
                    "  FROM historial_estados\n" +
                    "  WHERE id_radicado = :id_radicado\n" +
                    ")\n" +
                    "AND he.id_radicado = :id_radicado",
            nativeQuery=true
    )
    String encontrarEstado(int id_radicado);

    @Query(
            value = "SELECT MAX(p.id_radicado) AS id_radicado, MAX(p.titulo) AS titulo, MAX(p.descripcion) AS descripcion, MAX(p.anexo) AS anexo, MAX(p.id_usuario) AS id_usuario,\n" +
                    "MAX(p.id_prioridad) AS id_prioridad, MAX(p.id_area) AS id_area, MAX(p.id_tipo) AS id_tipo\n" +
                    "FROM pqrs p\n" +
                    "INNER JOIN historial_estados he ON p.id_radicado = he.id_radicado\n" +
                    "INNER JOIN estado e ON he.id_estado = e.id_estado\n" +
                    "WHERE he.fecha_cambio >= DATE_SUB(NOW(), INTERVAL 24 HOUR)\n" +
                    "GROUP BY p.id_radicado\n" +
                    "ORDER BY MAX(he.fecha_cambio) DESC",
            nativeQuery=true
    )
    List<Pqrs> encontrarUltimosRadicados();

    @Query(
            value = "SELECT * FROM pqrs p WHERE p.id_tipo = :id_tipo",
            nativeQuery = true
    )
    List<Pqrs> findAllByTipo(int id_tipo);
}
