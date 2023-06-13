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
@Table(name = "prioridad")
public class Prioridad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_prioridad;

    @Column(nullable = false, length = 60)
    private String descripcion;

    @JsonIgnoreProperties("prioridad")
    @OneToMany(mappedBy = "prioridad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pqrs> pqrsList = new ArrayList<>();
}
