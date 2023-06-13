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
@Table(name = "pregunta")
public class FQA implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_fqa;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String respuesta;
}
