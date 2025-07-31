package com.api.infrastructure.usuario.repository;

import com.api.domain.usuario.model.Usuario;
import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import com.api.infrastructure.usuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositoryJpa extends BaseRespositoryJpa<UsuarioEntity, Integer> {

    boolean existsByCorreo(String correo);

//    @Query("SELECT u FROM Usuario u WHERE " +
//            "LOWER(u.usuario) LIKE LOWER(CONCAT('%', :filtro, '%'))")
//    List<UsuarioEntity> findByUsuario(String filtro);
}
