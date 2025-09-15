package com.api.application.produccion.estanque.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
public class EstanqueRequestDto {

    private Integer id;
    private LocalDate fechaCreacion;

    @NotBlank(message = "El nombre del estanque es obligatorio!")
    private String estanque;

    @NotBlank(message = "Las coordenadas son obligatorias!")
    private String coordenadas;

    @NotNull(message = "El largo es obligatorio!")
    @Positive(message = "El largo debe ser un valor positivo!")
    private Double largo;

    @NotNull(message = "El ancho es obligatorio!")
    @Positive(message = "El ancho debe ser un valor positivo!")
    private Double ancho;

    @NotNull(message = "La profundidad es obligatoria!")
    @Positive(message = "La profundidad debe ser un valor positivo!")
    private Double profundidad;

    @NotNull(message = "El tipo de estanque es obligatorio!")
    private Integer tipoEstanqueId;
}
