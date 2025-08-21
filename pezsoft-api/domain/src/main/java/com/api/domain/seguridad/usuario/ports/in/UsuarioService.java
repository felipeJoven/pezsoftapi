package com.api.domain.seguridad.usuario.ports.in;

import com.api.domain.seguridad.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarUsuarios();
    Optional<Usuario> listarUsuarioPorId(Integer id);
    Usuario agregarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Integer id, Usuario usuario);
    void eliminarUsuario(Integer id);
//    String listarPerfil();
//    String actualizarPerfil(Usuario usuarioDto);
}
