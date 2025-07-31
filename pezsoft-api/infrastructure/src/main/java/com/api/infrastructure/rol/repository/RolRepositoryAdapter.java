package com.api.infrastructure.rol.repository;

import com.api.domain.rol.model.Rol;
import com.api.domain.rol.ports.out.RolRepository;
import com.api.infrastructure.rol.mapper.RolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RolRepositoryAdapter implements RolRepository {

    private final RolRepositoryJpa repositoryJpa;
    private final RolMapper mapper;

    @Override
    public List<Rol> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
