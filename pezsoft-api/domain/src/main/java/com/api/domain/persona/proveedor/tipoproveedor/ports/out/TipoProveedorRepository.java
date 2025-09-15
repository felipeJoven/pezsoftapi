package com.api.domain.persona.proveedor.tipoproveedor.ports.out;

import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;

import java.util.List;

public interface TipoProveedorRepository {

    List<TipoProveedor> buscarTodos();
}
