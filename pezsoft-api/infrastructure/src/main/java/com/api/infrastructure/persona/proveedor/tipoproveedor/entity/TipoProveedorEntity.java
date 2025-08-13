package com.api.infrastructure.persona.proveedor.tipoproveedor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tipo_proveedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "tipo_proveedor")
    private String tipoProveedor;
}
