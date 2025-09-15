/*
package com.api.application.lote.service;

import com.peces.pezSoft.dtos.LoteDto;
import com.peces.pezSoft.model.Especie;
import com.peces.pezSoft.model.Lote;
import com.peces.pezSoft.repository.EspecieRepository;
import com.peces.pezSoft.repository.LoteRepository;
import com.peces.pezSoft.repository.ProveedorRepository;
import com.peces.pezSoft.repository.EstanqueRepository;
import com.api.domain.lote.ports.in.LoteService;
import com.peces.pezSoft.utils.Message;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoteServiceImpl implements LoteService {

    private LoteRepository loteRepository;
    private EspecieRepository especieRepository;
    private EstanqueRepository estanqueRepository;
    private ProveedorRepository proveedorRepository;
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> verLotes(String filtro) {
        try {
            List<Lote> lotes;
            if (filtro != null && !filtro.isEmpty()) {
                lotes = loteRepository.findByLoteAndFechaSiembra(filtro);
            } else {
                lotes = loteRepository.findAll();
            }
            // Se realiza una lista con el dto para ocultar datos
            List<LoteDto> loteDtos = lotes.stream()
                    .map(lote -> {
                        // Operación para caluclar los dias de siembra con la fecha actual
                        Long diasDeSiembra = ChronoUnit.DAYS.between(lote.getFechaSiembra(), LocalDate.now());
                        // Mapear el lote al dto y asignar el valor calculado a diasDeSiembra
                        LoteDto loteDto = modelMapper.map(lote, LoteDto.class);
                        loteDto.setDiasCultivados(diasDeSiembra);
                        return loteDto;
                    })
                    .collect(Collectors.toList());
            if (!lotes.isEmpty()) {
                return ResponseEntity.ok(loteDtos);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_VER + "lotes!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> verLotePorId(Integer id) {
    
            Optional<Lote> loteOptional = loteRepository.findById(id);
            if (loteOptional.isPresent()) {
                Lote lote = loteOptional.get();
                // Operación para caluclar los dias de siembra con la fecha actual
                Long diasDeSiembra = ChronoUnit.DAYS.between(lote.getFechaSiembra(), LocalDate.now());
                // Mapear el lote al dto y asignar el valor calculado a diasDeSiembra
                LoteDto loteDto = modelMapper.map(lote, LoteDto.class);
                loteDto.setDiasCultivados(diasDeSiembra);
                return ResponseEntity.ok(loteDto);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Message.MENSAJE_ERROR_LISTAR_ID + id);
            }
    }

    @Override
    public ResponseEntity<?> agregarLote(LoteDto loteDto) {
    
            boolean existeLote = loteRepository.existsByLote(loteDto.getLote());
            if (!existeLote) {
                // Configurar ModelMapper para ignorar los campos que no deben cambiar
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                TypeMap<LoteDto, Lote> typeMap = modelMapper.getTypeMap(LoteDto.class, Lote.class);
                if (typeMap == null) {
                    typeMap = modelMapper.createTypeMap(LoteDto.class, Lote.class);
                }
                typeMap.addMappings(mapper -> {
                    mapper.skip(Lote::setId);
                    mapper.skip(Lote::setEspecie);
                    mapper.skip(Lote::setEstanque);
                    mapper.skip(Lote::setProveedor);
                });
                Lote lote = modelMapper.map(loteDto, Lote.class);
                // Validar que el dia de siembra no sea futuro
                LocalDate fechaSiembra = loteDto.getFechaSiembra();
                if (fechaSiembra.isAfter(LocalDate.now())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("La fecha de siembra no puede ser futura!");
                }
                
                lote.setPecesIniciales(loteDto.getNumeroPeces());
                
                Especie especie = especieRepository.findById(loteDto.getEspecieId())
                        .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada!"));
                lote.setEspecie(especie);
                Estanque estanque = estanqueRepository.findById(loteDto.getEstanqueId())
                        .orElseThrow(() -> new EntityNotFoundException("Unidad productiva no encontrada!"));
                lote.setEstanque(estanque);
                
                if (estanque.getEstado() == 0) {
                    estanque.setEstado(1);
                    estanqueRepository.save(estanque);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("La unidad productiva no está disponible!");
                }
                Proveedor proveedor = proveedorRepository.findById(loteDto.getProveedorId())
                        .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado!"));
                lote.setProveedor(proveedor);
                lote.setFechaCreacion(LocalDate.now());
                loteRepository.save(lote);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Message.MENSAJE_EXITOSO_GUARDADO + "un lote");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(String.format(Message.MENSAJE_ERROR_EXISTE, "el lote"));
            }        
    }

    @Override
    public ResponseEntity<?> actualizarLote(Integer id, LoteDto loteDto) {
    
            Optional<Lote> loteOptional = loteRepository.findById(id);
            if (loteOptional.isPresent()) {
                // Verifica si existe el lote en la bd
                boolean existeLote = loteRepository.existsByLote(loteDto.getLote());
                Lote lote = loteOptional.get();
                // Validaciones para actualizar con datos que no existen en la bd y poder actualizar otros campos
                if (!loteDto.getLote().equals(lote.getLote()) && existeLote) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(String.format(Message.MENSAJE_ERROR_EXISTE, "este lote"));
                }
                // Validar que el dia de siembra no sea futuro
                LocalDate fechaSiembra = loteDto.getFechaSiembra();
                if (!fechaSiembra.equals(lote.getFechaSiembra()) && fechaSiembra.isAfter(LocalDate.now())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("La fecha de siembra no puede ser futura!");
                }
                // Asignarle valor a animalesInicial y no permitir cambio si hay salida de peces
                if (lote.getPecesIniciales() == lote.getNumeroPeces()) {
                    loteDto.setPecesIniciales(loteDto.getNumeroPeces());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("El lote no se puede editar porque ya está en uso!");
                }
                // Verificar que existan unidades productivas, especies y proveedores en la bd
                Especie especieActualizada = especieRepository.findById(loteDto.getEspecieId())
                        .orElseThrow(() -> new EntityNotFoundException("Especie no encontrada!"));
                lote.setEspecie(especieActualizada);
                // Validar si es otra unidad productiva
                if (loteDto.getEstanqueId() != lote.getEstanque().getId()) {
                    Estanque estanqueActualizada = estanqueRepository.findById(loteDto.getEstanqueId())
                            .orElseThrow(() -> new EntityNotFoundException("Unidad productiva no encontrada!"));
                    // Validar el estado de la unidad productiva
                    if (estanqueActualizada.getEstado() != 0) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("La unidad productiva no está disponible!");
                    }
                    // Cambiar estado a la unidad productiva anterior
                    Estanque unidadAnterior = lote.getEstanque();
                    unidadAnterior.setEstado(0);
                    estanqueRepository.save(unidadAnterior);
                    // Cambiar el estado de la unidad productiva a ocupada
                    estanqueActualizada.setEstado(1);
                    estanqueRepository.save(estanqueActualizada);
                    lote.setEstanque(estanqueActualizada);
                }
                Proveedor proveedorActualizado = proveedorRepository.findById(loteDto.getProveedorId())
                        .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado!"));
                lote.setProveedor(proveedorActualizado);
                // Configurar ModelMapper para ignorar los campos que no deben cambiar
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                TypeMap<LoteDto, Lote> typeMap = modelMapper.getTypeMap(LoteDto.class, Lote.class);
                if (typeMap == null) {
                    typeMap = modelMapper.createTypeMap(LoteDto.class, Lote.class);
                }
                typeMap.addMappings(mapper -> {
                    mapper.skip(Lote::setId);
                    mapper.skip(Lote::setEspecie);
                    mapper.skip(Lote::setEstanque);
                    mapper.skip(Lote::setProveedor);
                    mapper.skip(Lote::setFechaCreacion);
                });
                // Aplicar la actualización
                modelMapper.map(loteDto, lote);
                loteRepository.save(lote);
                return ResponseEntity.ok(Message.MENSAJE_EXITOSO_ACTUALIZADO + "el lote");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_LISTAR_ID + id);
            }
    }

    @Override
    public ResponseEntity<?> eliminarLote(Integer id) {
    
            Optional<Lote> loteOptional = loteRepository.findById(id);
            if (loteOptional.isPresent()) {
                Lote lote = loteOptional.get();
                if (lote.getNumeroPeces() != lote.getPecesIniciales()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("El lote no se puede eliminar porque ya está en uso!");
                }
                // Cambiar el estado de la unidad productiva a disponible
                Estanque estanque = estanqueRepository.findById(lote.getEstanque().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Unidad productiva no encontrada!"));
                if (estanque.getEstado() == 1) {
                    estanque.setEstado(0);
                    estanqueRepository.save(estanque);
                }
                loteRepository.delete(lote);
                return ResponseEntity.ok(Message.MENSAJE_EXITOSO_ELIMINADO + "este lote");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_LISTAR_ID + id);
            }
    }
}*/
