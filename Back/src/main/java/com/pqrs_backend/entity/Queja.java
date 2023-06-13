package com.pqrs_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "queja")
public class Queja implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_queja;

    @Column(nullable = false, length = 20)
    private String nombre_usuario;

    @Column(nullable = false, length = 300)
    private String descripcion;
}
