package com.pqrs_backend.repository;

import com.pqrs_backend.entity.HistorialEstados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialEstadosRepository extends JpaRepository<HistorialEstados, Integer> {
    @Query(
            value = "SELECT he.*\n" +
                    "FROM HISTORIAL_ESTADOS he\n" +
                    "INNER JOIN (\n" +
                    "    SELECT id_radicado, MAX(fecha_cambio) AS ultima_fecha\n" +
                    "    FROM HISTORIAL_ESTADOS\n" +
                    "    GROUP BY id_radicado\n" +
                    ") subquery ON he.id_radicado = subquery.id_radicado AND he.fecha_cambio = subquery.ultima_fecha\n" +
                    "INNER JOIN ESTADO e ON he.id_estado = e.id_estado\n" +
                    "INNER JOIN PQRS p ON he.id_radicado = p.id_radicado\n" +
                    "WHERE he.id_estado = :id_estado AND p.id_tipo = :id_tipo",
            nativeQuery = true
    )
    List<HistorialEstados> filtroPorEstadoYTipo(int id_estado, int id_tipo);
}
