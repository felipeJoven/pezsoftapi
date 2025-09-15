package com.api.application.produccion.estanque.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EstanqueResponseDto {

    private Integer id;
    private LocalDate fechaCreacion;
    private String estanque;
    private String coordenadas;
    private double largo;
    private double ancho;
    private double area;
    private double profundidad;
    private boolean estado;
    private String tipoEstanque;
}
