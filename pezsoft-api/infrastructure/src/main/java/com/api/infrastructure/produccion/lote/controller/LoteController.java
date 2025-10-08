package com.api.infrastructure.produccion.lote.controller;

import com.api.application.produccion.lote.dto.LoteRequestDto;
import com.api.domain.produccion.lote.model.Lote;
import com.api.domain.produccion.lote.ports.in.LoteService;
import com.api.infrastructure.produccion.lote.mapper.LoteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("lote")
@CrossOrigin(origins="*")
public class LoteController {

    private final LoteService loteService;
    private final LoteMapper loteMapper;

    @GetMapping("")
    public ResponseEntity<?> obtenerLotes(@RequestParam(required = false) String filtro) {

        List<Lote> lotes = loteService.verLotes(filtro);
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLoteId(@PathVariable Integer id) {
        Optional<Lote> loteId = loteService.verLotePorId(id);
        return ResponseEntity.ok(loteId);
    }

    @PostMapping("")
    public ResponseEntity<?> crearLote(@Valid @RequestBody LoteRequestDto requestDto) {

//        Lote lote = loteMapper
//        return loteService.agregarLote(loteDto);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLote(@PathVariable Integer id, @Valid @RequestBody LoteRequestDto requestDto) {

//        Lote lote = loteMapper
//        return loteService.actualizarLote(id, loteDto);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarLote(@PathVariable Integer id) {
        loteService.eliminarLote(id);
        return ResponseEntity.noContent().build();
    }
}
