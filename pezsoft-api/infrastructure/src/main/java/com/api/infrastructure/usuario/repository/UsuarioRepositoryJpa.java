/*
package com.api.infrastructure.usuario.repository;

import com.api.domain.usuario.model.Usuario;
import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositoryJpa extends BaseRespositoryJpa<Usuario, Integer> {

    // Buscar un usuario mediante su nombre
    Optional<Usuario> findByEmail(String email);

    // Buscar un usuario mediante su username
    Optional<Usuario> findByUsuario(String usuario);

    // Verificar si un usuario existe en la BD
    Boolean existsByEmail(String email);

    // Encontrar usuario por username y rol
    List<Usuario> findByRolName(String filtro);
}*/
