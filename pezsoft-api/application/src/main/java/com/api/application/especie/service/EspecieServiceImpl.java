package com.api.application.especie.service;

import com.api.domain.especie.model.Especie;
import com.api.domain.especie.ports.in.EspecieService;
import com.api.domain.especie.ports.out.EspecieRepository;
import com.api.application.utils.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EspecieServiceImpl implements EspecieService {

    private EspecieRepository especieRepository;

    @Override
    public List<Especie> listarEspecies(String filtro) {
        List<Especie> especies;
        if (filtro != null && !filtro.isEmpty()) {
            especies = especieRepository.findByEspecie(filtro);
        } else {
            especies = especieRepository.findAll();
        }
        return especies;
    }

    /*@Override
    public Optional<Especie> listarEspeciePorId(Integer id) {
        try {
            Optional<Especie> optionalEspecie = especieRepository.findById(id);
            if (optionalEspecie.isPresent()) {
                return ResponseEntity.ok(optionalEspecie);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_ID + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public String agregarEspecie(Especie especie) {
        try {
            boolean existeEspecie = especieRepository.existsByEspecie(especie.getEspecie());
            if (existeEspecie) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(String.format(Message.MENSAJE_ERROR_EXISTE, "la especie"));
            } else {
                especie.setFechaCreacion(LocalDate.now());
                especieRepository.save(especie);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Message.MENSAJE_EXITOSO_GUARDADO + "una especie");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public String actualizarEspecie(Integer id, Especie especie) {
        try {
            boolean existeEspecie = especieRepository.existsByEspecie(especie.getEspecie());
            Optional<Especie> especieOptional = especieRepository.findById(id);
            if (especieOptional.isPresent()) {
                Especie especieActualizada = especieOptional.get();
                if (!especie.getEspecie().equals(especieActualizada.getEspecie()) && existeEspecie) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(String.format(Message.MENSAJE_ERROR_EXISTE, "esta especie!"));
                }
                especieActualizada.setEspecie(especie.getEspecie());
                especieRepository.save(especieActualizada);
                return ResponseEntity.ok(Message.MENSAJE_EXITOSO_ACTUALIZADO + "la especie");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_ID + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }

    @Override
    public String eliminarEspecie(Integer id) {
        try {
            Optional<Especie> optionalEspecie = especieRepository.findById(id);
            if (optionalEspecie.isPresent()) {
                Especie especie = optionalEspecie.get();
                especieRepository.delete(especie);
                return ResponseEntity.ok(Message.MENSAJE_EXITOSO_ELIMINADO + "esta especie");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_ID + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
        }
    }*/
}