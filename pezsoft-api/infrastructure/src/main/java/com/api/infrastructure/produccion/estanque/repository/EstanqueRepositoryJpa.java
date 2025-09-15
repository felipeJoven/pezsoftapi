package com.api.infrastructure.produccion.estanque.repository;

import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import com.api.infrastructure.produccion.estanque.entity.EstanqueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstanqueRepositoryJpa extends BaseRespositoryJpa<EstanqueEntity, Integer> {

    @Query("SELECT e FROM estanque e WHERE " +
            "LOWER(e.estanque) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(e.coordenadas) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<EstanqueEntity> findByEstanqueAndCoordenadas(@Param("filtro") String filtro);

    @Query("SELECT COUNT(e) > 0 FROM estanque e WHERE e.estanque = :estanque")
    boolean existsByEstanque(@Param("estanque") String estanque);

    @Query("SELECT COUNT(e) > 0 FROM estanque e WHERE e.coordenadas = :coordenadas")
    boolean existsByCoordenadas(@Param("coordenadas") String coordenadas);
}
