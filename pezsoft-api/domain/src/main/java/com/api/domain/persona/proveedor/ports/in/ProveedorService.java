package com.api.domain.persona.proveedor.ports.in;

import com.api.domain.persona.proveedor.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {

    List<Proveedor> listarProveedores(String filtro);
    Optional<Proveedor> listarProveedorPorId(Integer id);
    Proveedor agregarProveedor(Proveedor proveedor);
    Proveedor actualizarProveedor(Integer id, Proveedor proveedorNuevo);
    void eliminarProveedor(Integer id);
}
