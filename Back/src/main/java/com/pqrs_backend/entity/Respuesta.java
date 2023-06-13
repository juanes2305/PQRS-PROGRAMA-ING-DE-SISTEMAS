package com.pqrs_backend.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "respuesta")
public class Respuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_respuesta;

    @Column(nullable = false, length = 300)
    private String respuesta;

    private double calificacion;

    @Column(nullable = false, length = 300)
    private String anexo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_radicado")
    private Pqrs pqrs;
}
