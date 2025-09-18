package com.api.infrastructure.persona.proveedor.repository;

import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.domain.persona.proveedor.ports.out.ProveedorRepository;
import com.api.infrastructure.persona.proveedor.entity.ProveedorEntity;
import com.api.infrastructure.persona.proveedor.mapper.ProveedorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProveedorRepositoryAdapter implements ProveedorRepository {

    private final ProveedorRepositoryJpa proveedorRepositoryJpa;
    private final ProveedorMapper proveedorMapper;

    @Override
    public List<Proveedor> findAll() {
        return proveedorRepositoryJpa.findAll()
                .stream()
                .map(proveedorMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Proveedor> findById(Integer id) {
        return proveedorRepositoryJpa.findById(id).map(proveedorMapper::toDomain);
    }

    @Override
    public List<Proveedor> findByFilter(String filtro) {
        return proveedorRepositoryJpa.findByRazonSocialAndNumeroIdentificacion(filtro)
                .stream()
                .map(proveedorMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        ProveedorEntity entity = proveedorMapper.toEntity(proveedor);
        ProveedorEntity nuevoProveedor = proveedorRepositoryJpa.save(entity);
        return proveedorMapper.toDomain(nuevoProveedor);
    }

    @Override
    public void delete(Proveedor proveedor) {
        proveedorRepositoryJpa.delete(proveedorMapper.toEntity(proveedor));
    }

    @Override
    public boolean existeRazonSocial(String razonSocial) {
        return proveedorRepositoryJpa.existsByRazonSocial(razonSocial);
    }

    @Override
    public boolean existeNumeroIdentificacion(String numero) {
        return proveedorRepositoryJpa.existsByNumeroIdentificacion(numero);
    }
}
