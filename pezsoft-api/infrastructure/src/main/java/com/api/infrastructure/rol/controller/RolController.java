package com.api.infrastructure.rol.controller;

import com.api.domain.rol.model.Rol;
import com.api.domain.rol.ports.in.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rol")
@CrossOrigin("*")
public class RolController {

    private RolService rolService;

    @GetMapping("")
    public ResponseEntity<?> obtenerRoles() {
        List<Rol> roles = rolService.listarRoles();
        return ResponseEntity.ok(roles);
    }

}
