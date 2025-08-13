package com.api.infrastructure.produccion.modulo.tipoestanque.repository;

import com.api.domain.produccion.modulo.tipoestanque.model.TipoEstanque;
import com.api.domain.produccion.modulo.tipoestanque.ports.out.TipoEstanqueRepository;
import com.api.infrastructure.produccion.modulo.tipoestanque.mapper.TipoEstanqueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TipoEstanqueRepositoryAdapter implements TipoEstanqueRepository {

    private final TipoEstanqueRepositoryJpa repositoryJpa;
    private final TipoEstanqueMapper mapper;

    @Override
    public List<TipoEstanque> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
