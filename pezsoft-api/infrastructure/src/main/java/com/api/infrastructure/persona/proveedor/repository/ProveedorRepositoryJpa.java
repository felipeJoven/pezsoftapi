package com.api.infrastructure.persona.proveedor.repository;

import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import com.api.infrastructure.persona.proveedor.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepositoryJpa extends BaseRespositoryJpa<ProveedorEntity, Integer> {

    @Query("SELECT p FROM proveedor p WHERE " +
            "LOWER(p.razonSocial) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "CAST(p.numeroIdentificacion AS STRING) LIKE CONCAT('%', :filtro, '%')")
    List<ProveedorEntity> findByRazonSocialAndNumeroIdentificacion(@Param("filtro") String filtro);

    boolean existsByRazonSocial(String razonSocial);

    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}
