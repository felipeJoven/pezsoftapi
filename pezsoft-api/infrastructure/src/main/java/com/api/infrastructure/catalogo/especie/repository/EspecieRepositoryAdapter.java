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

    private final EspecieRepositoryJpa especieRepositoryJpa;
    private final EspecieMapper especieMapper;

    @Override
    public List<Especie> findAll() {
        return especieRepositoryJpa.findAll()
                .stream()
                .map(especieMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Especie> findByFilter(String filtro) {
        return especieRepositoryJpa.findByEspecie(filtro)
                .stream()
                .map(especieMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Especie> findById(Integer id) {
        return especieRepositoryJpa.findById(id).map(especieMapper::toDomain);
    }

    @Override
    public Especie save(Especie especie) {
        EspecieEntity entity = especieRepositoryJpa.save(especieMapper.toEntity(especie));
        EspecieEntity nuevaEspecie = especieRepositoryJpa.save(entity);
        return especieMapper.toDomain(nuevaEspecie);
    }

    @Override
    public void delete(Especie especie) {
        especieRepositoryJpa.delete(especieMapper.toEntity(especie));
    }


    @Override
    public boolean existsByEspecie(String especie) {
        return especieRepositoryJpa.existsByEspecie(especie);
    }
}
