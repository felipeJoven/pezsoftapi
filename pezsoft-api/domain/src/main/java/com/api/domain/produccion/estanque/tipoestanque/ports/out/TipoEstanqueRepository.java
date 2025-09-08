package com.api.domain.produccion.estanque.tipoestanque.ports.out;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;

import java.util.List;

public interface TipoEstanqueRepository {

    List<TipoEstanque> findAll();
}
