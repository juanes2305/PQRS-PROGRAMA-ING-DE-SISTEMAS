package com.pqrs_backend.repository;

import com.pqrs_backend.dto.RespuestaDto;
import com.pqrs_backend.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {

    @Query(
            value = "SELECT * FROM respuesta r WHERE r.id_usuario = :id",
            nativeQuery = true
    )
    List<Respuesta> findRespuestasByIdUsuario(int id);

    @Query(
            value = "SELECT * FROM respuesta r WHERE r.id_radicado = :id_radicado",
            nativeQuery = true
    )
    Respuesta findRespuestaByRadicado(int id_radicado);
}
