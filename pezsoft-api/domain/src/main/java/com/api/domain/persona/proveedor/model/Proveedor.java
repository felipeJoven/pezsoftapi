package com.api.domain.persona.proveedor.model;

import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

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
    private TipoIdentificacion tipoIdentificacion;
    private TipoProveedor tipoProveedor;
}
