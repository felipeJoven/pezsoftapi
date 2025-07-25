package com.api.domain.especie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Especie {
   
    private Integer id;
    private String especie;
    private LocalDate fechaCreacion;
}