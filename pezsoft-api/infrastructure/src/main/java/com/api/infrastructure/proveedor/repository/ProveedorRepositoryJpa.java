/*
package com.api.infrastructure.proveedor.repository;

import com.api.domain.proveedor.model.Proveedor;
import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepositoryJpa extends BaseRespositoryJpa<Proveedor, Integer> {

    // Verifica si existe un proveedor en la BD
    Boolean existsByNumeroIdentificacion(Long numero);

    // Encontrar proveedor por razon social y número de identificación
    @Query("SELECT p FROM Proveedor p WHERE " +
            "LOWER(p.razonSocial) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "CAST(p.numeroIdentificacion AS STRING) LIKE CONCAT('%', :filtro, '%')")
    List<Proveedor> findByRazonSocialAndNumeroIdentificacion(@Param("filtro") String filtro);
}*/
