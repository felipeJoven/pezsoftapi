package com.api.infrastructure.seguridad.rol.controller;

import com.api.domain.seguridad.rol.model.Rol;
import com.api.domain.seguridad.rol.ports.in.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rol")
@CrossOrigin("*")
public class RolController {

    private final RolService rolService;

    @GetMapping("")
    public ResponseEntity<?> obtenerRoles() {
        List<Rol> roles = rolService.listarRoles();
        return ResponseEntity.ok(roles);
    }
}
