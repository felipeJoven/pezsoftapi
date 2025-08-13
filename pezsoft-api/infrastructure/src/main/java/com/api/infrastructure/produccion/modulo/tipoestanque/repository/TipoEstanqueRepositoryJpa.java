package com.api.infrastructure.produccion.modulo.tipoestanque.repository;

import com.api.infrastructure.produccion.modulo.tipoestanque.entity.TipoEstanqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEstanqueRepositoryJpa extends JpaRepository<TipoEstanqueEntity, Integer> {
}
