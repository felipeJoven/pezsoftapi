package com.api.infrastructure.persona.proveedor.tipoproveedor.repository;

import com.api.infrastructure.persona.proveedor.tipoproveedor.entity.TipoProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProveedorRepositoryJpa extends JpaRepository<TipoProveedorEntity, Integer> {
}
