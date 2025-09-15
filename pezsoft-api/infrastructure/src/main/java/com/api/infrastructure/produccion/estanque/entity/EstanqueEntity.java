package com.api.infrastructure.produccion.estanque.entity;

import com.api.infrastructure.base.entity.Base;
import com.api.infrastructure.produccion.estanque.tipoestanque.entity.TipoEstanqueEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "estanque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstanqueEntity extends Base {

    @Column(nullable = false)
    private String estanque;

    @Column(nullable = false)
    private String coordenadas;

    @Column(nullable = false)
    private double largo;

    @Column(nullable = false)
    private double ancho;

    @Column(nullable = false)
    private double profundidad;

    @Column(nullable = false)
    private boolean estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_estanque_id", nullable = false)
    private TipoEstanqueEntity tipoEstanque;

    @Transient
    public double getArea() {
        return largo * ancho;
    }
}
