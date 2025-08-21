package com.api.application.seguridad.usuario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilServiceImpl {

    //    @Override
//    public ResponseEntity<?> listarPerfil() {
//        try {
//            // Obtener la autenticación actual
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
//            }
//            // Obtener el email del usuario autenticado
//            String email = authentication.getName();
//            Usuario usuario = usuarioRepository.findByEmail(email)
//                    .orElseThrow(() -> new UsernameNotFoundException("No se encontró usuario!"));
//            // Mapear los datos del usuario a UsuarioDto
//            UsuarioDto usuarioDto = new UsuarioDto();
//            usuarioDto.setUsuario(usuario.getUsuario());
//            usuarioDto.setNombre(usuario.getNombre());
//            usuarioDto.setApellido(usuario.getApellido());
//            usuarioDto.setTelefono(usuario.getTelefono());
//            usuarioDto.setEmail(usuario.getEmail());
//            usuarioDto.setRolName(usuario.getRol().getName());
//            return ResponseEntity.ok(usuarioDto);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
//        }
//    }
//

    //    @Override
//    public ResponseEntity<?> actualizarPerfil(UsuarioDto usuarioDto) {
//        try {
//            // Obtener la autenticación actual
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
//            }
//            // Obtener el email del usuario autenticado
//            String email = authentication.getName();
//            Usuario usuario = usuarioRepository.findByEmail(email)
//                    .orElseThrow(() -> new UsernameNotFoundException("No se encontró usuario!"));
//            boolean existeEmail = usuarioRepository.existsByEmail(usuarioDto.getEmail());
//            // Verifica si el email existe y que sea el mismo que tiene el usuario
//            if (!usuarioDto.getEmail().equals(usuario.getEmail()) && existeEmail) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Message.MENSAJE_ERROR_EMAIL);
//            }
//            // Valida que las contraseñas coinciden
//            if (passwordsEqual(usuarioDto.getPassword(), usuarioDto.getConfirmPassword())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Message.MENSAJE_ERROR_PASSWORD);
//            }
//            // Mapear los datos del usuario a UsuarioDto
//            usuario.setUsuario(usuarioDto.getUsuario());
//            usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
//            usuario.setNombre(usuarioDto.getNombre());
//            usuario.setApellido(usuarioDto.getApellido());
//            usuario.setTelefono(usuarioDto.getTelefono());
//            usuario.setEmail(usuarioDto.getEmail());
//            usuarioRepository.save(usuario);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(String.format(Message.MENSAJE_EXITOSO_ACTUALIZADO + "el Usuario"));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Message.MENSAJE_ERROR_SERVIDOR + e.getMessage());
//        }
//    }
//
}
