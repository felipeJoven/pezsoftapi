/*
package com.api.infrastructure.lote.repository;

import com.api.domain.lote.model.Lote;
import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepositoryJpa extends BaseRespositoryJpa<Lote, Integer> {

    // Verificar si existe un lote con la misma unidad productiva
    Boolean existsByLote(String lote);

    // Encontrar una lista de lotes por nombre y fecha de siembra
    @Query("SELECT l FROM Lote l WHERE " +
            "LOWER(l.lote) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "CAST(l.fechaSiembra AS string) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Lote> findByLoteAndFechaSiembra(String filtro);
}
*/
