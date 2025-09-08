package com.api.application.produccion.lote.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoteDto {

    private Integer id;
    private String lote;
    private int numeroPeces;
    private int pecesIniciales;
    private LocalDate fechaSiembra;
    private Long diasCultivados;
    private int especieId;
    private int estanqueId;
    private int proveedorId;
    private String especieEspecie;
    private String estanqueEstanque;
    private String proveedorRazonSocial;
}
