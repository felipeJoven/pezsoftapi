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
    private final TipoEstanqueMapper mapper;

    @Override
    public List<TipoEstanque> buscarTodos() {
        return tipoEstanqueRepositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoEstanque> buscarPorId(Integer id) {
        return tipoEstanqueRepositoryJpa.findById(id).map(mapper::toDomain);

    }
}
