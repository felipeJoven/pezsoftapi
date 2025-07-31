package com.api.infrastructure.usuario.controller;

import com.api.domain.usuario.model.Usuario;
import com.api.domain.usuario.ports.in.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
@CrossOrigin("*")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping("")
//    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> obtenerUsuarios(@RequestParam(required = false) String filtro) {
        List<Usuario> usuarios = usuarioService.listarUsuarios(filtro);
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
