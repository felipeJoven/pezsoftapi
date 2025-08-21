package com.api.infrastructure.seguridad.usuario.controller;

import com.api.application.seguridad.usuario.dto.UsuarioDto;
import com.api.domain.seguridad.usuario.model.Usuario;
import com.api.domain.seguridad.usuario.ports.in.UsuarioService;
import com.api.infrastructure.seguridad.usuario.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping("")
//    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> obtenerUsuarios(@RequestParam(required = false) String filtro) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioId = usuarioService.listarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioId);
    }


    @PostMapping("")
//    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.dtoToDomain(usuarioDto);
        Usuario nuevoUsuario = usuarioService.agregarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.dtoToDomain(usuarioDto);
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize(("hasAuthority('Admin')"))
    public ResponseEntity<?> borrarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
