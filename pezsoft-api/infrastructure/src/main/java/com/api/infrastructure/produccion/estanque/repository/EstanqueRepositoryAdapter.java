package com.api.infrastructure.produccion.estanque.repository;

import com.api.domain.produccion.estanque.model.Estanque;
import com.api.domain.produccion.estanque.ports.out.EstanqueRepository;
import com.api.infrastructure.produccion.estanque.entity.EstanqueEntity;
import com.api.infrastructure.produccion.estanque.mapper.EstanqueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EstanqueRepositoryAdapter implements EstanqueRepository {

    private final EstanqueRepositoryJpa estanqueRepositoryJpa;
    private final EstanqueMapper estanqueMapper;

    @Override
    public List<Estanque> findAll() {
        return estanqueRepositoryJpa.findAll()
                .stream()
                .map(estanqueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Estanque> findByFilter(String filtro) {
        return estanqueRepositoryJpa.findByEstanqueAndCoordenadas(filtro)
                .stream()
                .map(estanqueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Estanque> findById(Integer id) {
        return estanqueRepositoryJpa.findById(id).map(estanqueMapper::toDomain);
    }

    @Override
    public Estanque save(Estanque estanque) {
        EstanqueEntity entity = estanqueMapper.toEntity(estanque);
        EstanqueEntity nuevoEstanque = estanqueRepositoryJpa.save(entity);
        return estanqueMapper.toDomain(nuevoEstanque);
    }

    @Override
    public void delete(Estanque estanque) {
        estanqueRepositoryJpa.delete(estanqueMapper.toEntity(estanque));
    }

    @Override
    public boolean existeEstanque(String estanque) {
        return estanqueRepositoryJpa.existsByEstanque(estanque);
    }

    @Override
    public boolean existenCoordenadas(String coordenadas) {
        return estanqueRepositoryJpa.existsByCoordenadas(coordenadas);
    }
}
