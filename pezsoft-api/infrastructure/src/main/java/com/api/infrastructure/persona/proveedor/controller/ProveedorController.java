package com.api.infrastructure.persona.proveedor.controller;

import com.api.application.persona.proveedor.dto.ProveedorRequestDto;
import com.api.application.persona.proveedor.dto.ProveedorResponseDto;
import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.domain.persona.proveedor.ports.in.ProveedorService;
import com.api.infrastructure.persona.proveedor.mapper.ProveedorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("proveedor")
@CrossOrigin(origins="*")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final ProveedorMapper proveedorMapper;

    @GetMapping("")
    public ResponseEntity<?> obtenerProveedores(@RequestParam(required = false) String filtro) {

        List<Proveedor> proveedores = proveedorService.listarProveedores(filtro);

        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProveedorPorId(@PathVariable Integer id) {

        Optional<Proveedor> proveedorId = proveedorService.listarProveedorPorId(id);

        return ResponseEntity.ok(proveedorId);
    }

    @PostMapping("")
    public ResponseEntity<?> crearProveedor(@RequestBody ProveedorRequestDto requestDto) {

        Proveedor proveedor = proveedorMapper.requestToDomain(requestDto);
        Proveedor proveedorNuevo = proveedorService.agregarProveedor(proveedor);

        ProveedorResponseDto responseDto = proveedorMapper.domainToResponse(proveedorNuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarProveedor(@PathVariable Integer id, @RequestBody ProveedorRequestDto requestDto) {

        Proveedor proveedor = proveedorMapper.requestToDomain(requestDto);
        Proveedor proveedorActualizado = proveedorService.actualizarProveedor(id, proveedor);

        ProveedorResponseDto responseDto = proveedorMapper.domainToResponse(proveedorActualizado);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarProveedor(@PathVariable Integer id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
