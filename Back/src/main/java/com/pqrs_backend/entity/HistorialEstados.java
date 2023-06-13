package com.pqrs_backend.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "historial_estados")
public class HistorialEstados implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_historial;

    private LocalDateTime fecha_cambio;

    @ManyToOne
    @JoinColumn(name = "id_radicado")
    private Pqrs pqrs;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
}
