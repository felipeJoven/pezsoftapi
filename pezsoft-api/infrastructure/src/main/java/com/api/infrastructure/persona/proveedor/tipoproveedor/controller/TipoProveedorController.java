package com.api.infrastructure.persona.proveedor.tipoproveedor.controller;

import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.domain.persona.proveedor.tipoproveedor.ports.in.TipoProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tipo-proveedor")
@CrossOrigin(origins="*")
public class TipoProveedorController {

    private final TipoProveedorService tipoProveedorService;

    @GetMapping("")
    public ResponseEntity<?> obtenerTiposProveedores() {
        List<TipoProveedor> tipos = tipoProveedorService.verTipos();
        return ResponseEntity.ok(tipos);
    }
}
