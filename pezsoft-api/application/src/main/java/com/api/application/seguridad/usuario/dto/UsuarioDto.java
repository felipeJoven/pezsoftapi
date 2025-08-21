package com.api.application.seguridad.usuario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String usuario;
//    private String password;
//    private String confirmPassword;
    private String correo;
    private Long telefono;
    private LocalDate fechaCreacion;
//    private Integer rolId;
//    private String rolName;
}
