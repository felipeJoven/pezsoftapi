package com.api.application.produccion.lote.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoteResponseDto {

    private Integer id;
    private LocalDate fechaCreacion;
    private String lote;
    private LocalDate fechaSiembra;
    private Integer numeroPeces;
    private Integer pecesIniciales;
    private Integer diasCultivados;
    private Integer especieId;
    private String especie;
    private Integer estanqueId;
    private String estanque;
    private Integer proveedorId;
    private String proveedor;
}
