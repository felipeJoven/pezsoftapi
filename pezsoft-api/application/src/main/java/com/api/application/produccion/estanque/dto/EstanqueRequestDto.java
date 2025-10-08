package com.api.application.produccion.estanque.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EstanqueRequestDto {

    private Integer id;
    private LocalDate fechaCreacion;

    @NotBlank(message = "El nombre del estanque es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre del estanque debe tener entre 2 y 50 caracteres")
    private String estanque;

    @NotBlank(message = "Las coordenadas son obligatorias")
    @Pattern(
            regexp = "^-?\\d{1,2}\\.\\d{1,6},\\s?-?\\d{1,3}\\.\\d{1,6}$",
            message = "Las coordenadas deben tener el formato 'latitud,longitud', por ejemplo: '4.609710,-74.081750'"
    )
    private String coordenadas;

    @NotNull(message = "El largo es obligatorio")
    @DecimalMin(value = "5.0", message = "El largo debe ser mínimo de 5 metros!")
    @DecimalMax(value = "100.0", message = "El largo debe ser máximo de 100 metros!")
    private Double largo;

    @NotNull(message = "El ancho es obligatorio")
    @DecimalMin(value = "1.0", message = "El ancho debe ser mínimo de 1 metro!")
    @DecimalMax(value = "50.0", message = "El ancho debe ser máximo de 50 metros!")
    private Double ancho;

    @NotNull(message = "La profundidad es obligatoria")
    @DecimalMin(value = "0.5", message = "La profundidad debe ser mínimo de 0.5 metros!")
    @DecimalMax(value = "5.0", message = "La profundidad debe ser máximo de 5 metros!")
    private Double profundidad;

    @NotNull(message = "El tipo de estanque es obligatorio")
    private Integer tipoEstanqueId;
}
