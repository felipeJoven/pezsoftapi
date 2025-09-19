package com.api.infrastructure.persona.proveedor.entity;

import com.api.infrastructure.base.entity.Base;
import com.api.infrastructure.persona.proveedor.tipoproveedor.entity.TipoProveedorEntity;
import com.api.infrastructure.persona.tipoidentificacion.entity.TipoIdentificacionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "proveedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorEntity extends Base {

    @Column(nullable = false)
    private String razonSocial;

    @Column(nullable = false)
    private String numeroIdentificacion;

    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String celular;

    private String telefono;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String direccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_identificacion_id", nullable = false)
    private TipoIdentificacionEntity tipoIdentificacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_proveedor_id", nullable = false)
    private TipoProveedorEntity tipoProveedor;
}
