package com.api.infrastructure.catalogo.especie.controller;

import com.api.domain.catalogo.especie.model.Especie;
import com.api.domain.catalogo.especie.ports.in.EspecieService;
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
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEspeciePorId(@PathVariable Integer id) {
        Optional<Especie> especieId = especieService.listarEspeciePorId(id);
        return ResponseEntity.ok(especieId);
    }

    @PostMapping("")
    public ResponseEntity<?> crearEspecie(@RequestBody Especie especie) {
        Especie nuevaEspecie = especieService.agregarEspecie(especie);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEspecie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarEspecie(@PathVariable Integer id, @RequestBody Especie especie) {
        Especie especieActualizada = especieService.actualizarEspecie(id, especie);
        return ResponseEntity.ok(especieActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEspecie(@PathVariable Integer id) {
        especieService.eliminarEspecie(id);
        return ResponseEntity.noContent().build();
    }
}