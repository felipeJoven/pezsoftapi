package com.api.domain.produccion.lote.ports.out;

import com.api.domain.produccion.lote.model.Lote;

import java.util.List;
import java.util.Optional;

public interface LoteRepository {

    List<Lote> findAll();
    List<Lote> findByFilter(String filtro);
    Optional<Lote> findById(Integer id);
    Lote save(Lote lote);
    void delete(Lote lote);
    boolean existsByLote(String lote);
}
