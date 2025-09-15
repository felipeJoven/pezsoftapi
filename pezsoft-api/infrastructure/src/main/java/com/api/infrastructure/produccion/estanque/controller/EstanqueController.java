package com.api.infrastructure.produccion.estanque.controller;

import com.api.application.produccion.estanque.dto.EstanqueRequestDto;
import com.api.application.produccion.estanque.dto.EstanqueResponseDto;
import com.api.domain.produccion.estanque.model.Estanque;
import com.api.domain.produccion.estanque.ports.in.EstanqueService;
import com.api.infrastructure.produccion.estanque.mapper.EstanqueMapper;
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
@RequestMapping("estanque")
@CrossOrigin(origins="*")
public class EstanqueController {

    private EstanqueService estanqueService;
    private EstanqueMapper estanqueMapper;

    @GetMapping("")
    public ResponseEntity<?> obtenerEstanques(@RequestParam(required = false) String filtro) {

        List<Estanque> estanques = estanqueService.listarEstanque(filtro);

        List<EstanqueResponseDto> responseDto = estanques.stream()
                .map(estanqueMapper::domainToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEstanquePorId(@PathVariable Integer id) {

        Optional<Estanque> estanqueId = estanqueService.listarEstanquePorId(id);

        Optional<EstanqueResponseDto> responseDto = estanqueId
                .map(estanqueMapper::domainToResponse);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<?> crearEstanque(@Valid @RequestBody EstanqueRequestDto estanqueDto) {

        Estanque estanque = estanqueMapper.requestToDomain(estanqueDto);
        Estanque estanqueNuevo = estanqueService.agregarEstanque(estanque);

        EstanqueResponseDto responseDto = estanqueMapper.domainToResponse(estanqueNuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarEstanque(@PathVariable Integer id, @Valid @RequestBody EstanqueRequestDto estanqueDto) {

        Estanque estanque = estanqueMapper.requestToDomain(estanqueDto);
        Estanque estanqueActualizado = estanqueService.actualizarEstanque(id, estanque);

        EstanqueResponseDto responseDto = estanqueMapper.domainToResponse(estanqueActualizado);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEstanque(@PathVariable Integer id) {
        estanqueService.eliminarEstanque(id);
        return ResponseEntity.noContent().build();
    }
}
