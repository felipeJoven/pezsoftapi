package com.api.domain.produccion.estanque.tipoestanque.ports.out;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;

import java.util.List;
import java.util.Optional;

public interface TipoEstanqueRepository {

    List<TipoEstanque> buscarTodos();
    Optional<TipoEstanque> buscarPorId(Integer id);
}
