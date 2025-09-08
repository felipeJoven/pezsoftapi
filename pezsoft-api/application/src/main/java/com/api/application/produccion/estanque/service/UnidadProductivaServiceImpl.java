/*
package com.api.application.unidadproductiva.service;

import com.peces.pezSoft.utils.Message;
import com.peces.pezSoft.repository.ModuloRepository;
import com.api.domain.unidadproductiva.ports.in.TipoEstanqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModuloServiceImpl implements TipoEstanqueService {

    private ModuloRepository unidadProductivaRepository;

    @Override
    public ResponseEntity<?> listarUnidadP(String filtro) {
        try {
            List<Modulo> unidades;
            if (filtro != null && !filtro.isEmpty()) {
                unidades = unidadProductivaRepository.findByUnidadPAndCoordenadas(filtro);
            } else {
                unidades = unidadProductivaRepository.findAll();
            }
            if (!unidades.isEmpty()) {
                return ResponseEntity.ok(unidades);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_VER + "unidades productivas!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> listarUnidadPPorId(Integer id) {
        try {
            Optional<Modulo> optionalUnidad = unidadProductivaRepository.findById(id);
            if (optionalUnidad.isPresent()) {
                return ResponseEntity.ok(optionalUnidad);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Message.MENSAJE_ERROR_LISTAR_ID + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> agregarUnidadP(Modulo unidadP) {
        try {
            String unidad = unidadP.getUnidadP();
            String coordenadas = unidadP.getCoordenadas();
            boolean existeUnidad = unidadProductivaRepository.existsByUnidadP(unidad);
            boolean existeCoordenadas = unidadProductivaRepository.existsByCoordenadas(coordenadas);
            if (existeUnidad && existeCoordenadas) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("La unidad productiva y las coordenadas no estan disponibles!");
            } else if (existeUnidad) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(String.format(Message.MENSAJE_ERROR_EXISTE, "esta unidad productiva"));
            } else if (existeCoordenadas) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(String.format(Message.MENSAJE_ERROR_EXISTE, "estas coordenadas"));
            }
            unidadP.setEstado(0);
            unidadP.setFechaCreacion(LocalDate.now());
            unidadProductivaRepository.save(unidadP);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Message.MENSAJE_EXITOSO_GUARDADO + "una unidad productiva");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> actualizarUnidadP(Integer id, Modulo unidadP) {
        try {
            boolean existeUnidad = unidadProductivaRepository.existsByUnidadP(unidadP.getUnidadP());
            boolean existeCoordenadas = unidadProductivaRepository.existsByCoordenadas(unidadP.getCoordenadas());
            Optional<Modulo> unidadOptional = unidadProductivaRepository.findById(id);
            if (unidadOptional.isPresent()) {
                Modulo unidadActualizada = unidadOptional.get();
                if (
                        existeUnidad && !unidadP.getUnidadP().equals(unidadActualizada.getUnidadP()) &&
                                existeCoordenadas && !unidadP.getCoordenadas().equals(unidadActualizada.getCoordenadas())
                ) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("La unidad productiva y las coordenadas no estan disponibles!");
                } else if (!unidadP.getUnidadP().equals(unidadActualizada.getUnidadP()) && existeUnidad) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(String.format(Message.MENSAJE_ERROR_EXISTE, "esta unidad productiva"));
                } else if (!unidadP.getCoordenadas().equals(unidadActualizada.getCoordenadas()) && existeCoordenadas) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(String.format(Message.MENSAJE_ERROR_EXISTE, "estas coordenadas"));
                }
                // Copia todas las propiedades de unidadP a unidadActualizada excepto el ID
                BeanUtils.copyProperties(unidadP, unidadActualizada, "id", "fechaCreacion", "estado");
                unidadProductivaRepository.save(unidadActualizada);
                return ResponseEntity.ok(Message.MENSAJE_EXITOSO_ACTUALIZADO + "la unidad productiva");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_LISTAR_ID + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> eliminarUnidadP(Integer id) {
        try {
            Optional<Modulo> optionalUnidad = unidadProductivaRepository.findById(id);
            if (optionalUnidad.isPresent()) {
                Modulo unidadP = optionalUnidad.get();
                unidadProductivaRepository.delete(unidadP);
                return ResponseEntity.ok(Message.MENSAJE_EXITOSO_ELIMINADO + "esta unidad productiva");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_LISTAR_ID + id);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }
}*/
