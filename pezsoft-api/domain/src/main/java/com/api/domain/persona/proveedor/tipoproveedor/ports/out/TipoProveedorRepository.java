package com.api.domain.persona.proveedor.tipoproveedor.ports.out;

import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;

import java.util.List;
import java.util.Optional;

public interface TipoProveedorRepository {

    List<TipoProveedor> findAll();
    Optional<TipoProveedor> findById(Integer id);
}
