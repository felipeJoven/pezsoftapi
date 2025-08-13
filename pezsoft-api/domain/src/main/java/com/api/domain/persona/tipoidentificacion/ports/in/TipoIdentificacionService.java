package com.api.domain.persona.tipoidentificacion.ports.in;

import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;

import java.util.List;

public interface TipoIdentificacionService {
    
    List<TipoIdentificacion> verTipos();
}
