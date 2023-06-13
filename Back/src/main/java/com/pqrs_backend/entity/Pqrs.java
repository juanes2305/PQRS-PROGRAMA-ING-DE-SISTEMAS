package com.pqrs_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pqrs")
public class Pqrs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_radicado;

    @Column(nullable = false, length = 40)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String descripcion;

    @Column(length = 300)
    private String anexo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_prioridad")
    private Prioridad prioridad;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

    @JsonIgnoreProperties("pqrs")
    @OneToMany(mappedBy = "pqrs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialEstados> historialEstadosList = new ArrayList<>();

    @JsonIgnoreProperties("pqrs")
    @OneToMany(mappedBy = "pqrs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();
}
