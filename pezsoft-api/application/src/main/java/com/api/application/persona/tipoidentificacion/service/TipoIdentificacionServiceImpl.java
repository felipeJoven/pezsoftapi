package com.api.application.persona.tipoidentificacion.service;

import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;
import com.api.domain.persona.tipoidentificacion.ports.in.TipoIdentificacionService;
import com.api.domain.persona.tipoidentificacion.ports.out.TipoIdentificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

    private final TipoIdentificacionRepository tipoIdentificacionRepository;

    @Override
    public List<TipoIdentificacion> verTipos() {
            return tipoIdentificacionRepository.findAll();
    }
}
