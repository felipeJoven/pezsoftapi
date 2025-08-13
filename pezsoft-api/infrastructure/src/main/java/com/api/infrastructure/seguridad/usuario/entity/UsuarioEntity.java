package com.api.infrastructure.seguridad.usuario.entity;

import com.api.infrastructure.base.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity extends Base {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String correo;

//    @Column(nullable = false)
//    private String clave;
    
    @Column(nullable = false)
    private String telefono;

//    @ManyToOne(optional = false)
//    @JoinColumn(nullable = false)
//    private Rol rol;
}
