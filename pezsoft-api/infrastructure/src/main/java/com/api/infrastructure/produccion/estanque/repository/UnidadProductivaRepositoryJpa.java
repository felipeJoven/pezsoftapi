/*
package com.api.infrastructure.unidadproductiva.repository;

import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuloRepositoryJpa extends BaseRespositoryJpa<Modulo, Integer> {

    // Verificar si exite la unidad productiva
    @Query("SELECT COUNT(u) > 0 FROM Modulo u WHERE u.unidadP = :unidadP")
    boolean existsByUnidadP(@Param("unidadP") String unidadP);

    // Verificar si exiten las coordenadas
    @Query("SELECT COUNT(u) > 0 FROM Modulo u WHERE u.coordenadas = :coordenadas")
    boolean existsByCoordenadas(@Param("coordenadas") String coordenadas);

    // Encontrar una lista de unidades productivas por unidad productiva y coordenadas
    @Query("SELECT u FROM Modulo u WHERE " +
            "LOWER(u.unidadP) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(u.coordenadas) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Modulo> findByUnidadPAndCoordenadas(@Param("filtro") String filtro);
}*/
