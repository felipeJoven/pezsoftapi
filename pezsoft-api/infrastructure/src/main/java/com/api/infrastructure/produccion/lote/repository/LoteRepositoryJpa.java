package com.api.infrastructure.produccion.lote.repository;

import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import com.api.infrastructure.produccion.lote.entity.LoteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepositoryJpa extends BaseRespositoryJpa<LoteEntity, Integer> {

    @Query("SELECT l FROM lote l WHERE " +
            "LOWER(l.lote) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "CAST(l.fechaSiembra AS string) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<LoteEntity> findByLoteAndFechaSiembra(String filtro);

    boolean existsByLote(String lote);
}
