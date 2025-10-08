package com.api.application.produccion.lote.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoteRequestDto {

    private Integer id;
    private LocalDate fechaCreacion;
    private String lote;
    private LocalDate fechaSiembra;
    private Integer numeroPeces;
    private Integer pecesIniciales;
    private Integer diasCultivados;
    private Integer especieId;
    private Integer estanqueId;
    private Integer proveedorId;
}
