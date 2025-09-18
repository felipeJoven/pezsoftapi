package com.api.application.produccion.estanque.tipoestanque.service;

import com.api.application.utils.Message;
import com.api.domain.exception.NotFoundException;
import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import com.api.domain.produccion.estanque.tipoestanque.ports.in.TipoEstanqueService;
import com.api.domain.produccion.estanque.tipoestanque.ports.out.TipoEstanqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEstanqueServiceImpl implements TipoEstanqueService {

    private final TipoEstanqueRepository tipoEstanqueRepository;

    @Override
    public List<TipoEstanque> verTipos() {

        List<TipoEstanque> tipos = tipoEstanqueRepository.findAll();

        if (tipos.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR + "tipos de estanques!");
        }

        return tipos;
    }
}
