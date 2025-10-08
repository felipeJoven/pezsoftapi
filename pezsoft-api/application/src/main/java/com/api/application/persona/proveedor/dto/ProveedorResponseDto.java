package com.api.application.persona.proveedor.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProveedorResponseDto {

    private Integer id;
    private LocalDate fechaCreacion;
    private String razonSocial;
    private String numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String celular;
    private String telefono;
    private String correo;
    private String direccion;
    private Integer tipoIdentificacionId;
    private String tipoIdentificacion;
    private Integer tipoProveedorId;
    private String tipoProveedor;
}