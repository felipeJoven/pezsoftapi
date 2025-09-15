package com.api.domain.persona.tipoidentificacion.ports.out;

import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;

import java.util.List;

public interface TipoIdentificacionRepository {

    List<TipoIdentificacion> buscarTodos();
}
