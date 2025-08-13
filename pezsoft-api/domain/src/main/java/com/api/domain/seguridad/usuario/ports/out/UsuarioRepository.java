package com.api.domain.seguridad.usuario.ports.out;

import com.api.domain.seguridad.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

//    List<Usuario> findByUsuario(String correo);
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
//    Boolean existsByEmail(String email);
//    List<Usuario> findByRolName(String filtro);
}
