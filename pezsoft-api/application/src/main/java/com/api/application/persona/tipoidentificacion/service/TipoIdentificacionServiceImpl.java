package com.api.application.persona.tipoidentificacion.service;

import com.api.application.utils.Message;
import com.api.domain.exception.NotFoundException;
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

        List<TipoIdentificacion> tipos = tipoIdentificacionRepository.findAll();

        if (tipos.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR + "tipos de identificaciones!");
        }

        return tipos;
    }
}
