package com.api.infrastructure.produccion.estanque.tipoestanque.repository;

import com.api.infrastructure.produccion.estanque.tipoestanque.entity.TipoEstanqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEstanqueRepositoryJpa extends JpaRepository<TipoEstanqueEntity, Integer> {
}
