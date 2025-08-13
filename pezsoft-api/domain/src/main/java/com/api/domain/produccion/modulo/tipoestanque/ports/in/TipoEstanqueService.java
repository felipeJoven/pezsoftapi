package com.api.domain.produccion.modulo.tipoestanque.ports.in;

import com.api.domain.produccion.modulo.tipoestanque.model.TipoEstanque;

import java.util.List;

public interface TipoEstanqueService {

    List<TipoEstanque> verTipos();
}