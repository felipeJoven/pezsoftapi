package com.api.infrastructure.persona.tipoidentificacion.repository;

import com.api.infrastructure.persona.tipoidentificacion.entity.TipoIdentificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIdentificacionRepositoryJpa extends JpaRepository<TipoIdentificacionEntity, Integer> {
}
