package com.api.infrastructure.persona.proveedor.tipoproveedor.repository;

import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.domain.persona.proveedor.tipoproveedor.ports.out.TipoProveedorRepository;
import com.api.infrastructure.persona.proveedor.tipoproveedor.mapper.TipoProveedorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TipoProveedorRepositoryAdapter implements TipoProveedorRepository {

    private final TipoProveedorRepositoryJpa tipoProveedorRepositoryJpa;
    private final TipoProveedorMapper tipoProveedorMapper;

    @Override
    public List<TipoProveedor> findAll() {
        return tipoProveedorRepositoryJpa.findAll()
                .stream()
                .map(tipoProveedorMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoProveedor> findById(Integer id) {
        return tipoProveedorRepositoryJpa.findById(id).map(tipoProveedorMapper::toDomain);
    }
}
