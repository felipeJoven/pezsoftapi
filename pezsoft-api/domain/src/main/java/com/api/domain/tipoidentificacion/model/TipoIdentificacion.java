package com.api.domain.tipoidentificacion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoIdentificacion {

    private Integer id;
    private String name;
    private LocalDate fechaCreacion;
}
