package com.api.domain.seguridad.usuario.ports.in;

import com.api.domain.seguridad.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarUsuarios();
    Optional<Usuario> listarUsuarioPorId(Integer id);
//    String listarPerfil();
//    String agregarUsuario(Usuario usuarioDto);
//    String actualizarUsuario(Integer id, Usuario usuarioDto);
//    String actualizarPerfil(Usuario usuarioDto);
//    String eliminarUsuario(Integer id);
}
