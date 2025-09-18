package com.api.infrastructure.produccion.estanque.tipoestanque.repository;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import com.api.domain.produccion.estanque.tipoestanque.ports.out.TipoEstanqueRepository;
import com.api.infrastructure.produccion.estanque.tipoestanque.mapper.TipoEstanqueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TipoEstanqueRepositoryAdapter implements TipoEstanqueRepository {

    private final TipoEstanqueRepositoryJpa tipoEstanqueRepositoryJpa;
    private final TipoEstanqueMapper tipoEstanqueMapper;

    @Override
    public List<TipoEstanque> findAll() {
        return tipoEstanqueRepositoryJpa.findAll()
                .stream()
                .map(tipoEstanqueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoEstanque> findById(Integer id) {
        return tipoEstanqueRepositoryJpa.findById(id).map(tipoEstanqueMapper::toDomain);
    }
}
