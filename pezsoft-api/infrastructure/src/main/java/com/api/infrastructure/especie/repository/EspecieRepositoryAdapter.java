package com.api.infrastructure.especie.repository;

import com.api.domain.especie.model.Especie;
import com.api.domain.especie.ports.out.EspecieRepository;
import com.api.infrastructure.especie.mapper.EspecieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EspecieRepositoryAdapter implements EspecieRepository {

    private final EspecieRepositoryJpa repositoryJpa;
    private final EspecieMapper mapper;

    @Override
    public List<Especie> findByEspecie(String filtro) {
        return repositoryJpa.findByEspecie(filtro)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Especie> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    /*@Override
    public Optional<Especie> findById(Integer id) {
        return repositoryJpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEspecie(String nombre) {
        return repositoryJpa.existsByEspecie(nombre);
    }

    @Override
    public void save(Especie especie) {
        repositoryJpa.save(mapper.toEntity(especie));
    }

    @Override
    public void delete(Especie especie) {
        repositoryJpa.delete(mapper.toEntity(especie));
    }*/
}
