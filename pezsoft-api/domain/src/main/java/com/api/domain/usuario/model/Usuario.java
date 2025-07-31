package com.api.domain.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String usuario;
    private String nombre;
    private String apellido;
    private String email;
//    private String clave;
    private Long telefono;
//    private Integer rol;
}
