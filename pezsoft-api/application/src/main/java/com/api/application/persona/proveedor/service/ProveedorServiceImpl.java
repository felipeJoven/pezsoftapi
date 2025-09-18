package com.api.application.persona.proveedor.service;

import com.api.application.utils.Message;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.exception.PersistenceException;
import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.domain.persona.proveedor.ports.in.ProveedorService;
import com.api.domain.persona.proveedor.ports.out.ProveedorRepository;
import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.domain.persona.proveedor.tipoproveedor.ports.out.TipoProveedorRepository;
import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;
import com.api.domain.persona.tipoidentificacion.ports.out.TipoIdentificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final TipoIdentificacionRepository tipoIdentificacionRepository;
    private final TipoProveedorRepository tipoProveedorRepository;

    @Override
    public List<Proveedor> listarProveedores(String filtro) {

        List<Proveedor> proveedores = (filtro != null && !filtro.isEmpty()
                ? proveedorRepository.findByFilter(filtro)
                : proveedorRepository.findAll());

        if (proveedores.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR + "proveedores!");
        }

        return proveedores;
    }

    @Override
    public Optional<Proveedor> listarProveedorPorId(Integer id) {

        Optional<Proveedor> proveedorId = proveedorRepository.findById(id);

        if (proveedorId.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id);
        }

        return proveedorId;
    }

    @Override
    @Transactional
    public Proveedor agregarProveedor(Proveedor proveedor) {

        boolean existeRazonSocial = proveedorRepository.existeRazonSocial(proveedor.getRazonSocial());
        boolean existeNumeroIdentificacion = proveedorRepository.existeNumeroIdentificacion(proveedor.getNumeroIdentificacion());

        if (existeRazonSocial && existeNumeroIdentificacion) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "el proveedor");
        } else if (existeRazonSocial) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "la razón social");
        } else if (existeNumeroIdentificacion) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "el número de identificación");
        }

        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(proveedor.getTipoIdentificacion().getId())
                .orElseThrow(() -> new NotFoundException("Tipo de Identificación no encontrado!"));
        proveedor.setTipoIdentificacion(tipoIdentificacion);
        TipoProveedor tipoProveedor = tipoProveedorRepository.findById(proveedor.getTipoProveedor().getId())
                .orElseThrow(() -> new NotFoundException("Tipo de Proveedor no encontrado!"));

        proveedor.setTipoIdentificacion(tipoIdentificacion);
        proveedor.setTipoProveedor(tipoProveedor);
        proveedor.setFechaCreacion(LocalDate.now());
        proveedorRepository.save(proveedor);

        if (proveedor.getId() == null) {
            throw new PersistenceException(Message.MENSAJE_ERROR_NO_ID + "proveedor");
        }

        return proveedor;
    }

    @Override
    @Transactional
    public Proveedor actualizarProveedor(Integer id, Proveedor proveedorDto) {

//            boolean existeProveedor = proveedorRepository.existsByNumeroIdentificacion(proveedorDto.getNumeroIdentificacion());
//            Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
//            if (proveedorOptional.isPresent()) {
//                Proveedor proveedor = proveedorOptional.get();
//                // Validaciones para actualizar con datos que no existen en la bd y poder actualizar otros campos
//                if (!proveedorDto.getNumeroIdentificacion().equals(proveedor.getNumeroIdentificacion()) && existeProveedor) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body(String.format(Message.MENSAJE_ERROR_EXISTE, "este proveedor!"));
//                }
//                // Validar si los números de identificación y teléfono contienen la cantidad correcta de caracteres
//                String identificacion = String.valueOf(proveedorDto.getNumeroIdentificacion());
//                String telefono = String.valueOf(proveedorDto.getTelefono());
//                if (
//                        !proveedorDto.getNumeroIdentificacion().equals(proveedor.getNumeroIdentificacion()) &&
//                                identificacion.length() < 7 || identificacion.length() > 10
//                ) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body("El número de documento debe contener entre 7 y 10 números!");
//                }
//                if (!proveedorDto.getTelefono().equals(proveedor.getTelefono()) && telefono.length() != 10) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body("El télefono debe contener exactamente 10 números!");
//                }
//                // Verificar que exitan los tipos de identificación y proveedores en la bd
//                TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(proveedorDto.getTipoIdentificacionId())
//                        .orElseThrow(() -> new EntityNotFoundException("Tipo de Identificación no encontrado"));
//                proveedor.setTipoIdentificacion(tipoIdentificacion);
//                TipoProveedor tipoProveedor = tipoProveedorRepository.findById(proveedorDto.getTipoProveedorId())
//                        .orElseThrow(() -> new EntityNotFoundException("Tipo de Proveedor no encontrado"));
//                proveedor.setTipoProveedor(tipoProveedor);
//                proveedor.setNumeroIdentificacion(proveedorDto.getNumeroIdentificacion());
//                // Configurar ModelMapper para ignorar los campos que no deben cambiar
//                modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
//                modelMapper.typeMap(ProveedorDto.class, Proveedor.class).addMappings(mapper -> {
//                    mapper.skip(Proveedor::setId);
//                    mapper.skip(Proveedor::setFechaCreacion);
//                });
//                // Aplicar la actualización
//                modelMapper.map(proveedorDto, proveedor);
//                proveedorRepository.save(proveedor);
//                return ResponseEntity.ok(Message.MENSAJE_EXITOSO_ACTUALIZADO + "el proveedor");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(Message.MENSAJE_ERROR_LISTAR_ID + id);
//            }

        return null;
    }

    @Override
    @Transactional
    public void eliminarProveedor(Integer id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id));

        proveedorRepository.delete(proveedor);
    }
}
