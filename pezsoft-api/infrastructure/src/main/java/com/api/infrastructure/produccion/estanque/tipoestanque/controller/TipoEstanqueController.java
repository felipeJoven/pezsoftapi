package com.api.infrastructure.produccion.estanque.tipoestanque.controller;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import com.api.domain.produccion.estanque.tipoestanque.ports.in.TipoEstanqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tipo-estanque")
@CrossOrigin(origins = "*")
public class TipoEstanqueController {

    private final TipoEstanqueService tipoEstanqueService;

    @GetMapping("")
    public ResponseEntity<?> obtenerTiposEstanques() {
        List<TipoEstanque> tipos = tipoEstanqueService.verTipos();
        return ResponseEntity.ok(tipos);
    }
}
