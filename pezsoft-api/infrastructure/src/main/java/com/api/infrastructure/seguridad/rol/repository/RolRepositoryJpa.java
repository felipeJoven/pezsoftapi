package com.api.infrastructure.seguridad.rol.repository;

import com.api.infrastructure.seguridad.rol.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositoryJpa extends JpaRepository<RolEntity, Integer> {

//    Optional<Rol> findByName(String name);
}
