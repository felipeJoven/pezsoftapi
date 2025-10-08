package com.api.application.persona.proveedor.service;

import com.api.application.utils.MessageUtils;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.exception.IdNotGeneratedException;
import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.domain.persona.proveedor.ports.in.ProveedorService;
import com.api.domain.persona.proveedor.ports.out.ProveedorRepository;
import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.domain.persona.proveedor.tipoproveedor.ports.out.TipoProveedorRepository;
import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;
import com.api.domain.persona.tipoidentificacion.ports.out.TipoIdentificacionRepository;
import com.api.domain.utils.FiltroUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private static final Logger logger = LoggerFactory.getLogger(ProveedorServiceImpl.class);
    private final ProveedorRepository proveedorRepository;
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final TipoProveedorRepository tipoProveedorRepository;

    @Override
    public List<Proveedor> listarProveedores(String filtro) {

        List<Proveedor> proveedores;

        if (FiltroUtils.esFiltroValido(filtro)) {
            proveedores = proveedorRepository.findByFilter(filtro);
            logger.info("Proveedores filtrados: {}", proveedores.size());

            if (proveedores.isEmpty()) {
                logger.warn(MessageUtils.NO_ENCONTRADO + "proveedores con el filtro: {}", filtro);
                throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "proveedores!");
            }
        } else {
            proveedores = proveedorRepository.findAll();

            if (proveedores.isEmpty()) {
                logger.info(MessageUtils.NO_EXISTE, "proveedores");
            } else {
                logger.info("Proveedores encontrados: {}", proveedores.size());
            }
        }

        return proveedores;
    }

    @Override
    public Optional<Proveedor> listarProveedorPorId(Integer id) {

        Optional<Proveedor> proveedorId = proveedorRepository.findById(id);

        if (proveedorId.isEmpty()) {
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return proveedorId;
    }

    @Override
    @Transactional
    public Proveedor agregarProveedor(Proveedor proveedor) {

        boolean existeRazonSocial = proveedorRepository.existeRazonSocial(proveedor.getRazonSocial());
        boolean existeNumeroIdentificacion = proveedorRepository.existeNumeroIdentificacion(proveedor.getNumeroIdentificacion());

        if (existeRazonSocial && existeNumeroIdentificacion) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el proveedor");
        } else if (existeRazonSocial) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "la razón social");
        } else if (existeNumeroIdentificacion) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el número de identificación");
        }

        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(proveedor.getTipoIdentificacion().getId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "tipo de identificación"));

        TipoProveedor tipoProveedor = tipoProveedorRepository.findById(proveedor.getTipoProveedor().getId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "tipo de proveedor"));

        proveedor.setTipoIdentificacion(tipoIdentificacion);
        proveedor.setTipoProveedor(tipoProveedor);
        proveedor.setFechaCreacion(LocalDate.now());

        Proveedor guardarProveedor = proveedorRepository.save(proveedor);

        if (guardarProveedor.getId() == null) {
            throw new IdNotGeneratedException(MessageUtils.ID_NO_GENERADO + "proveedor");
        }

        return guardarProveedor;
    }

    @Override
    @Transactional
    public Proveedor actualizarProveedor(Integer id, Proveedor proveedorNuevo) {

        boolean existeRazonSocial = proveedorRepository.existeRazonSocial(proveedorNuevo.getRazonSocial());
        boolean existeNumeroIdentificacion = proveedorRepository.existeNumeroIdentificacion(proveedorNuevo.getNumeroIdentificacion());

        Proveedor proveedorActual = proveedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        boolean cambioRazonSocial = !proveedorNuevo.getRazonSocial().equalsIgnoreCase(proveedorActual.getRazonSocial());
        boolean cambioNumeroIdentificacion = !proveedorNuevo.getNumeroIdentificacion().equalsIgnoreCase(proveedorActual.getNumeroIdentificacion());

        if (existeRazonSocial && cambioRazonSocial && existeNumeroIdentificacion && cambioNumeroIdentificacion) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el proveedor");
        } else if (cambioRazonSocial && existeRazonSocial) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "la razón social");
        } else if (cambioNumeroIdentificacion && existeNumeroIdentificacion) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el número de identificación");
        }

        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(proveedorNuevo.getTipoIdentificacion().getId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "tipo de identificación"));

        TipoProveedor tipoProveedor = tipoProveedorRepository.findById(proveedorNuevo.getTipoProveedor().getId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "tipo de proveedor"));

        proveedorNuevo.setTipoIdentificacion(tipoIdentificacion);
        proveedorNuevo.setTipoProveedor(tipoProveedor);

        BeanUtils.copyProperties(proveedorNuevo, proveedorActual, "id", "fechaCreacion");

        return proveedorRepository.save(proveedorActual);
    }

    @Override
    @Transactional
    public void eliminarProveedor(Integer id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        proveedorRepository.delete(proveedor);
    }
}
