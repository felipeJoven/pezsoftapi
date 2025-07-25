package com.api.infrastructure.especie.controller;

import com.api.application.utils.Message;
import com.api.domain.especie.model.Especie;
import com.api.domain.especie.ports.in.EspecieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("especie")
@CrossOrigin(origins = "*")
public class EspecieController {

    private final EspecieService especieService;

    @GetMapping("")
    public ResponseEntity<?> obtenerEspecies(@RequestParam(required = false) String filtro) {
        List<Especie> especies = especieService.listarEspecies(filtro);
        if (especies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Message.MENSAJE_ERROR_VER + "especies!");
        }
        return ResponseEntity.ok(especies);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<?> obtenerEspecieId(@PathVariable Integer id) {
        Optional<Especie> especie = especieService.listarEspeciePorId(id);
        if (especie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Message.MENSAJE_ERROR_ID + id);
        }
        return ResponseEntity.ok(especie);
    }

    @PostMapping("")
    public ResponseEntity<?> crearEspecie(@RequestBody Especie especie) {
        return especieService.agregarEspecie(especie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarEspecie(@PathVariable Integer id, @RequestBody Especie especie) {
        return especieService.actualizarEspecie(id, especie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEspecie(@PathVariable Integer id) {
        return especieService.eliminarEspecie(id);
    }*/
}