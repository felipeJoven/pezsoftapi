package com.api.infrastructure.persona.tipoidentificacion.repository;

import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;
import com.api.domain.persona.tipoidentificacion.ports.out.TipoIdentificacionRepository;
import com.api.infrastructure.persona.tipoidentificacion.mapper.TipoIdentificacionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TipoIdentificacionRespositoryAdapter implements TipoIdentificacionRepository {

    private final TipoIdentificacionRepositoryJpa repositoryJpa;
    private final TipoIdentificacionMapper mapper;


    @Override
    public List<TipoIdentificacion> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
