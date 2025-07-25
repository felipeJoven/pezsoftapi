/*
package com.api.infrastructure.pesca.controller;

import com.api.application.pesca.dto.PescaDto;
import com.api.domain.pesca.ports.in.PescaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("pesca")
@CrossOrigin(origins = "*")
public class PescaController {

    private PescaService pescaService;

    @GetMapping("")
    public ResponseEntity<?> obtenerPescas(@RequestParam(required = false) String filtro) {
        return pescaService.verPescas(filtro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPescaId(@PathVariable Integer id) {
        return pescaService.verPescaPorId(id);
    }

    @PostMapping("")
    public ResponseEntity<?> crearPesca(@RequestBody PescaDto pescaDto) {
        return pescaService.agregarPesca(pescaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarPesca(@PathVariable Integer id, @RequestBody PescaDto pescaDto) {
        return pescaService.actualizarPesca(id, pescaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPesca(@PathVariable Integer id) {
        return pescaService.eliminarPesca(id);
    }
}*/
