package com.api.application.produccion.modulo.tipoestanque.service;

import com.api.domain.produccion.modulo.tipoestanque.model.TipoEstanque;
import com.api.domain.produccion.modulo.tipoestanque.ports.in.TipoEstanqueService;
import com.api.domain.produccion.modulo.tipoestanque.ports.out.TipoEstanqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEstanqueServiceImpl implements TipoEstanqueService {

    private final TipoEstanqueRepository tipoEstanqueRepository;

    @Override
    public List<TipoEstanque> verTipos() {
        return tipoEstanqueRepository.findAll();
    }
}
