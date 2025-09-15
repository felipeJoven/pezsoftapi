package com.api.infrastructure.seguridad.usuario.repository;

import com.api.domain.seguridad.usuario.model.Usuario;
import com.api.domain.seguridad.usuario.ports.out.UsuarioRepository;
import com.api.infrastructure.seguridad.usuario.entity.UsuarioEntity;
import com.api.infrastructure.seguridad.usuario.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepositoryJpa.findAll()
                .stream()
                .map(usuarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepositoryJpa.findById(id).map(usuarioMapper::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = usuarioRepositoryJpa.save(usuarioMapper.toEntity(usuario));
        UsuarioEntity nuevoUsuario = usuarioRepositoryJpa.save(entity);
        return usuarioMapper.toDomain(nuevoUsuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepositoryJpa.delete(usuarioMapper.toEntity(usuario));
    }

    @Override
    public boolean existsByEmail(String correo) {
        return usuarioRepositoryJpa.existsByCorreo(correo);
    }
}
