package com.api.infrastructure.persona.proveedor.controller;

import com.api.application.persona.proveedor.dto.ProveedorRequestDto;
import com.api.application.persona.proveedor.dto.ProveedorResponseDto;
import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.domain.persona.proveedor.ports.in.ProveedorService;
import com.api.infrastructure.persona.proveedor.mapper.ProveedorMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("proveedor")
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final ProveedorMapper proveedorMapper;

    @GetMapping("")
    public ResponseEntity<?> obtenerProveedores(@RequestParam(required = false) String filtro) {

        List<Proveedor> proveedores = proveedorService.listarProveedores(filtro);

        List<ProveedorResponseDto> responseDto = proveedores
                .stream()
                .map(proveedorMapper::domainToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProveedorPorId(@PathVariable Integer id) {

        Optional<Proveedor> proveedorId = proveedorService.listarProveedorPorId(id);

        Optional<ProveedorResponseDto> responseDto = proveedorId.map(proveedorMapper::domainToResponse);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<?> crearProveedor(@Valid @RequestBody ProveedorRequestDto requestDto) {

        Proveedor proveedor = proveedorMapper.requestToDomain(requestDto);
        Proveedor proveedorNuevo = proveedorService.agregarProveedor(proveedor);

        ProveedorResponseDto responseDto = proveedorMapper.domainToResponse(proveedorNuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarProveedor(@PathVariable Integer id, @Valid @RequestBody ProveedorRequestDto requestDto) {

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
