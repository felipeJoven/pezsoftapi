package com.api.infrastructure.rol.repository;

import com.api.infrastructure.rol.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositoryJpa extends JpaRepository<RolEntity, Integer> {

    //Metodo para poder buscar un rol mediante su nombre
//    Optional<Rol> findByName(String name);
}
