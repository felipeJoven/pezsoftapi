package com.api.domain.produccion.estanque.tipoestanque.ports.in;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;

import java.util.List;

public interface TipoEstanqueService {

    List<TipoEstanque> verTipos();
}