package com.api.domain.persona.tipoidentificacion.ports.out;

import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;

import java.util.List;
import java.util.Optional;

public interface TipoIdentificacionRepository {

    List<TipoIdentificacion> findAll();
    Optional<TipoIdentificacion> findById(Integer id);
}
