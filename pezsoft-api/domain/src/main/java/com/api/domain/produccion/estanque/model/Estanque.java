package com.api.domain.produccion.estanque.model;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estanque {

    private Integer id;
    private LocalDate fechaCreacion;
    private String estanque;
    private String coordenadas;
    private double largo;
    private double ancho;
    private double profundidad;
    private boolean estado;
    private TipoEstanque tipoEstanque;

    public double getArea() {
        return largo * ancho;
    }
}
