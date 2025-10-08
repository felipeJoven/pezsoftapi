package com.api.application.produccion.lote.service;

import com.api.application.utils.MessageUtils;
import com.api.domain.catalogo.especie.model.Especie;
import com.api.domain.catalogo.especie.ports.out.EspecieRepository;
import com.api.domain.exception.BadRequestException;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.IdNotGeneratedException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.domain.persona.proveedor.ports.out.ProveedorRepository;
import com.api.domain.produccion.estanque.model.Estanque;
import com.api.domain.produccion.estanque.ports.out.EstanqueRepository;
import com.api.domain.produccion.lote.model.Lote;
import com.api.domain.produccion.lote.ports.in.LoteService;
import com.api.domain.produccion.lote.ports.out.LoteRepository;
import com.api.domain.utils.FiltroUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoteServiceImpl implements LoteService {

    private static final Logger logger = LoggerFactory.getLogger(LoteServiceImpl.class);
    private final LoteRepository loteRepository;
    private final EspecieRepository especieRepository;
    private final EstanqueRepository estanqueRepository;
    private final ProveedorRepository proveedorRepository;

    @Override
    public List<Lote> verLotes(String filtro) {

        List<Lote> lotes;

        if (FiltroUtils.esFiltroValido(filtro)) {
            lotes = loteRepository.findByFilter(filtro);
            logger.info("Lotes filtrados: {}", lotes.size());

            if (lotes.isEmpty()) {
                logger.warn(MessageUtils.NO_ENCONTRADO + " lotes con filtro: {}", filtro);
                throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "lotes!");
            }
        } else {
            lotes = loteRepository.findAll();

            if (lotes.isEmpty()) {
                logger.warn(MessageUtils.NO_EXISTE, "lotes");
            } else {
                logger.info("Lotes encontrados: {}", lotes.size());
            }
        }

        return lotes;
    }

    @Override
    public Optional<Lote> verLotePorId(Integer id) {

        Optional<Lote> loteId = loteRepository.findById(id);

        if (loteId.isEmpty()) {
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }
        return loteId;
    }

    @Override
    @Transactional
    public Lote agregarLote(Lote lote) {

        boolean existeLote = loteRepository.existsByLote(lote.getLote());

        if (existeLote) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el lote");
        }

        LocalDate fechaSiembra = lote.getFechaSiembra();
        if (fechaSiembra.isAfter(LocalDate.now())) {
            throw new BadRequestException("La fecha de siembra no puede ser futura!");
        }

        lote.setPecesIniciales(lote.getNumeroPeces());

        Especie especie = especieRepository.findById(lote.getEspecie().getId())
                .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada!"));

        Proveedor proveedor = proveedorRepository.findById(lote.getProveedor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado!"));

        Estanque estanque = estanqueRepository.findById(lote.getEstanque().getId())
                .orElseThrow(() -> new EntityNotFoundException("Unidad productiva no encontrada!"));

        if (estanque.isEstado()) {
            estanque.setEstado(true);
            estanqueRepository.save(estanque);
        } else {
            throw new BadRequestException("La unidad productiva no está disponible!");
        }

        lote.setEspecie(especie);
        lote.setProveedor(proveedor);
        lote.setEstanque(estanque);
        lote.setFechaCreacion(LocalDate.now());

        Lote guardarLote = loteRepository.save(lote);

        if (guardarLote.getLote() == null) {
            throw new IdNotGeneratedException(MessageUtils.ID_NO_GENERADO + "lote");
        }

        return guardarLote;
    }

    @Override
    @Transactional
    public Lote actualizarLote(Integer id, Lote loteDto) {
        return null;
//
//        Lote loteActual = loteRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(Message.ID_NO_ENCONTRADO + id));
//
//
//            boolean existeLote = loteRepository.existsByLote(loteDto.getLote());
//            Lote lote = loteOptional.get();
//
//            if (!loteDto.getLote().equals(lote.getLote()) && existeLote) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(String.format(Message.YA_EXISTE, "este lote"));
//            }
//
//            LocalDate fechaSiembra = loteDto.getFechaSiembra();
//            if (!fechaSiembra.equals(lote.getFechaSiembra()) && fechaSiembra.isAfter(LocalDate.now())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("La fecha de siembra no puede ser futura!");
//            }
//
//            if (lote.getPecesIniciales() == lote.getNumeroPeces()) {
//                loteDto.setPecesIniciales(loteDto.getNumeroPeces());
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("El lote no se puede editar porque ya está en uso!");
//            }
//
//            Especie especieActualizada = especieRepository.findById(loteDto.getEspecieId())
//                    .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada!"));
//            lote.setEspecie(especieActualizada);
//
//            if (loteDto.getEstanqueId() != lote.getEstanque().getId()) {
//                Estanque estanqueActualizada = estanqueRepository.findById(loteDto.getEstanqueId())
//                        .orElseThrow(() -> new EntityNotFoundException("Unidad productiva no encontrada!"));
//
//                if (estanqueActualizada.getEstado() != 0) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body("La unidad productiva no está disponible!");
//                }
//                // Cambiar estado a la unidad productiva anterior
//                Estanque unidadAnterior = lote.getEstanque();
//                unidadAnterior.setEstado(0);
//                estanqueRepository.save(unidadAnterior);
//                // Cambiar el estado de la unidad productiva a ocupada
//                estanqueActualizada.setEstado(1);
//                estanqueRepository.save(estanqueActualizada);
//                lote.setEstanque(estanqueActualizada);
//            }
//            Proveedor proveedorActualizado = proveedorRepository.findById(loteDto.getProveedorId())
//                    .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado!"));
//            lote.setProveedor(proveedorActualizado);
//            // Configurar ModelMapper para ignorar los campos que no deben cambiar
//            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//            TypeMap<LoteDto, Lote> typeMap = modelMapper.getTypeMap(LoteDto.class, Lote.class);
//            if (typeMap == null) {
//                typeMap = modelMapper.createTypeMap(LoteDto.class, Lote.class);
//            }
//            typeMap.addMappings(mapper -> {
//                mapper.skip(Lote::setId);
//                mapper.skip(Lote::setEspecie);
//                mapper.skip(Lote::setEstanque);
//                mapper.skip(Lote::setProveedor);
//                mapper.skip(Lote::setFechaCreacion);
//            });
//            // Aplicar la actualización
//            modelMapper.map(loteDto, lote);
//            loteRepository.save(lote);
//            return loteActual;
    }

    @Override
    @Transactional
    public void eliminarLote(Integer id) {

        Lote lote = loteRepository.findById(id).
                orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        if (lote.getNumeroPeces() != lote.getPecesIniciales()) {
            throw new BadRequestException("El lote no se puede eliminar porque ya está en uso!");
        }

        Estanque estanque = estanqueRepository.findById(lote.getEstanque().getId())
                .orElseThrow(() -> new EntityNotFoundException("Unidad productiva no encontrada!"));
        if (estanque.isEstado()) {
            estanque.setEstado(false);
            estanqueRepository.save(estanque);
        }

        loteRepository.delete(lote);
    }
}
