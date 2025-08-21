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

    private final UsuarioRepositoryJpa repositoryJpa;
    private final UsuarioMapper mapper;

    @Override
    public List<Usuario> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return repositoryJpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = repositoryJpa.save(mapper.toEntity(usuario));
        UsuarioEntity nuevoUsuario = repositoryJpa.save(entity);
        return mapper.toDomain(nuevoUsuario);
    }

    @Override
    public void delete(Usuario usuario) {
        repositoryJpa.delete(mapper.toEntity(usuario));
    }

    @Override
    public boolean existsByEmail(String correo) {
        return repositoryJpa.existsByCorreo(correo);
    }
}
