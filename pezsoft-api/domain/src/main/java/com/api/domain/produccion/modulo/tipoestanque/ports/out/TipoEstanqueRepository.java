package com.api.domain.produccion.modulo.tipoestanque.ports.out;

import com.api.domain.produccion.modulo.tipoestanque.model.TipoEstanque;

import java.util.List;

public interface TipoEstanqueRepository {

    List<TipoEstanque> findAll();
}
