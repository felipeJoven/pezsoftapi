package com.api.infrastructure.seguridad.usuario.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PerfilController {

    //    @GetMapping("/perfil")
//    public ResponseEntity<?> obtenerPerfil() {
//        return usuarioService.listarPerfil();
//    }

//    @PutMapping("/perfil")
//    public ResponseEntity<?> actualizarPerfil(@RequestBody UsuarioDto usuarioDto) {
//        return usuarioService.actualizarPerfil(usuarioDto);
//    }
}
