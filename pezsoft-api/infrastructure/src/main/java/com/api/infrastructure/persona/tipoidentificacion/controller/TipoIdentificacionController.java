package com.api.infrastructure.persona.tipoidentificacion.controller;

import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;
import com.api.domain.persona.tipoidentificacion.ports.in.TipoIdentificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tipo-identificacion")
@CrossOrigin(origins="*")
public class TipoIdentificacionController {

    private final TipoIdentificacionService tipoIdentificacionService;

    @GetMapping("")
    public ResponseEntity<?> obtenerTiposIdentificaciones() {
        List<TipoIdentificacion> tipos = tipoIdentificacionService.verTipos();
        return ResponseEntity.ok(tipos);
    }
}
