package com.api.infrastructure.tipoestanque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo_estanque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoEstanqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "tipo_estanque")
    private String name;
}
