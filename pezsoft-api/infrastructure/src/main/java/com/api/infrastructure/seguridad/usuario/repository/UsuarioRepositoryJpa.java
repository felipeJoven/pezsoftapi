package com.api.infrastructure.seguridad.usuario.repository;

import com.api.infrastructure.base.repository.BaseRespositoryJpa;
import com.api.infrastructure.seguridad.usuario.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoryJpa extends BaseRespositoryJpa<UsuarioEntity, Integer> {

    //    @Query("SELECT u FROM Usuario u WHERE " +
//            "LOWER(u.usuario) LIKE LOWER(CONCAT('%', :filtro, '%'))")
//    List<UsuarioEntity> findByUsuario(String filtro);

    boolean existsByCorreo(String correo);
}
