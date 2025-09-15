package com.api.infrastructure.catalogo.especie.repository;

import com.api.infrastructure.catalogo.especie.entity.EspecieEntity;
import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecieRepositoryJpa extends BaseRespositoryJpa<EspecieEntity, Integer> {

    @Query("SELECT e FROM especie e WHERE " +
            "LOWER(e.especie) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<EspecieEntity> findByEspecie(@Param("filtro") String filtro);

    boolean existsByEspecie(String especie);
}