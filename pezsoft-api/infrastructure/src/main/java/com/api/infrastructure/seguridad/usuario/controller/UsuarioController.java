package com.api.infrastructure.seguridad.usuario.controller;

import com.api.domain.seguridad.usuario.model.Usuario;
import com.api.domain.seguridad.usuario.ports.in.UsuarioService;
import lombok.RequiredArgsConstructor;
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

//    @GetMapping("/perfil")
//    public ResponseEntity<?> obtenerPerfil() {
//        return usuarioService.listarPerfil();
//    }
//
//    @PostMapping("")
//    @PreAuthorize("hasAuthority('Admin')")
//    public ResponseEntity<?> agregarUsuario(@RequestBody UsuarioDto usuarioDto) {
//        return usuarioService.agregarUsuario(usuarioDto);
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('Admin')")
//    public ResponseEntity<?> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioDto usuarioDto) {
//        return usuarioService.actualizarUsuario(id, usuarioDto);
//    }
//
//    @PutMapping("/perfil")
//    public ResponseEntity<?> actualizarPerfil(@RequestBody UsuarioDto usuarioDto) {
//        return usuarioService.actualizarPerfil(usuarioDto);
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize(("hasAuthority('Admin')"))
//    public ResponseEntity<?> borrarUsuario(@PathVariable Integer id) {
//        return usuarioService.eliminarUsuario(id);
//    }
}
