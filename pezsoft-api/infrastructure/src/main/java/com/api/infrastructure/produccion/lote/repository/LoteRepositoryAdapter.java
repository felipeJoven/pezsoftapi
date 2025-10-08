package com.api.infrastructure.produccion.lote.repository;

import com.api.domain.produccion.lote.model.Lote;
import com.api.domain.produccion.lote.ports.out.LoteRepository;
import com.api.infrastructure.produccion.lote.entity.LoteEntity;
import com.api.infrastructure.produccion.lote.mapper.LoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoteRepositoryAdapter implements LoteRepository {

    private final LoteRepositoryJpa loteRepositoryJpa;
    private final LoteMapper loteMapper;

    @Override
    public List<Lote> findAll() {
        return loteRepositoryJpa.findAll()
                .stream()
                .map(loteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Lote> findByFilter(String filtro) {
        return loteRepositoryJpa.findByLoteAndFechaSiembra(filtro)
                .stream()
                .map(loteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Lote> findById(Integer id) {
        return loteRepositoryJpa.findById(id).map(loteMapper::toDomain);
    }

    @Override
    public Lote save(Lote lote) {
        LoteEntity entity = loteMapper.toEntity(lote);
        LoteEntity nuevoLote = loteRepositoryJpa.save(entity);
        return loteMapper.toDomain(nuevoLote);
    }

    @Override
    public void delete(Lote lote) {
        loteRepositoryJpa.delete(loteMapper.toEntity(lote));
    }

    @Override
    public boolean existsByLote(String lote) {
        return loteRepositoryJpa.existsByLote(lote);
    }
}
