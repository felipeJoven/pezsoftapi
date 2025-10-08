package com.api.domain.persona.proveedor.ports.out;

import com.api.domain.persona.proveedor.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository {

    List<Proveedor> findAll();
    List<Proveedor> findByFilter(String filtro);
    Optional<Proveedor> findById(Integer id);
    Proveedor save(Proveedor proveedor);
    void delete(Proveedor proveedor);
    boolean existeRazonSocial(String razonSocial);
    boolean existeNumeroIdentificacion(String numeroIdentificacion);
}
