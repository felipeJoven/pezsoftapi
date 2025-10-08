package com.api.domain.produccion.lote.model;

import com.api.domain.catalogo.especie.model.Especie;
import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.domain.produccion.estanque.model.Estanque;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lote {

    private Integer id;
    private LocalDate fechaCreacion;
    private String lote;
    private LocalDate fechaSiembra;
    private int pecesIniciales;
    private int  numeroPeces;
    private Especie especie;
    private Proveedor proveedor;
    private Estanque estanque;

    public Long getDiasCultivados() {
        return ChronoUnit.DAYS.between(fechaSiembra, LocalDate.now());
    }
}
