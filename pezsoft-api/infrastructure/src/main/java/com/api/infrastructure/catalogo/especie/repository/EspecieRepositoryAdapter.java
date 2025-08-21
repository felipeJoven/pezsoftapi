package com.api.infrastructure.catalogo.especie.repository;

import com.api.domain.catalogo.especie.model.Especie;
import com.api.domain.catalogo.especie.ports.out.EspecieRepository;
import com.api.infrastructure.catalogo.especie.entity.EspecieEntity;
import com.api.infrastructure.catalogo.especie.mapper.EspecieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

    @Override
    public Optional<Especie> findById(Integer id) {
        return repositoryJpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Especie save(Especie especie) {
        EspecieEntity entity = repositoryJpa.save(mapper.toEntity(especie));
        EspecieEntity nuevaEspecie = repositoryJpa.save(entity);
        return mapper.toDomain(nuevaEspecie);
    }

    @Override
    public void delete(Especie especie) {
        repositoryJpa.delete(mapper.toEntity(especie));
    }


    @Override
    public boolean existsByEspecie(String especie) {
        return repositoryJpa.existsByEspecie(especie);
    }
}
