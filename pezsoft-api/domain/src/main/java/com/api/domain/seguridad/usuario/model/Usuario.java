package com.api.domain.seguridad.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Integer id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String correo;
//    private String clave;
    private String telefono;
//    private Integer rol;
    private String fechaCreacion;
}
