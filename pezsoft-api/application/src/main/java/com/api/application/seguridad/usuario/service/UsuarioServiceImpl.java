package com.api.application.seguridad.usuario.service;

import com.api.application.seguridad.usuario.dto.UsuarioDto;
import com.api.application.utils.Message;
import com.api.domain.exception.BadRequestException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.seguridad.usuario.model.Usuario;
import com.api.domain.seguridad.usuario.ports.in.UsuarioService;
import com.api.domain.seguridad.usuario.ports.out.UsuarioRepository;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
//    private RolRepository rolRepository;
//    private PasswordEncoder passwordEncoder;

    private boolean passwordsEqual(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    @Override
    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR + "usuarios!");
        }

        return usuarios;
    }

    @Override
    public Optional<Usuario> listarUsuarioPorId(Integer id) {

        Optional<Usuario> usuarioId = usuarioRepository.findById(id);

        if (usuarioId.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id);
        }

        return usuarioId;
    }

    @Override
    public Usuario agregarUsuario(Usuario usuario) {

        UsuarioDto usuarioDto = new UsuarioDto();
        boolean existeCorreo = usuarioRepository.existsByEmail(usuarioDto.getCorreo());


        String correo = usuarioDto.getCorreo();
        if (correo.isEmpty()) {
            throw new BadRequestException(Message.MENSAJE_ERROR_CORREO_VACIO);
        }

        if (existeCorreo) {
            throw new BadRequestException(Message.MENSAJE_ERROR_CORREO);
        }

        String telefono = String.valueOf(usuarioDto.getTelefono());
        if (telefono.length() != 10) {
            throw new BadRequestException(Message.MENSAJE_ERROR_TELEFONO);
        }

//            if (passwordsEqual(usuarioDto.getPassword(), usuarioDto.getConfirmPassword())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Message.MENSAJE_ERROR_PASSWORD);
//            }

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setCorreo(correo);
        usuario.setTelefono(telefono);
//            usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        // Verificar que existan los roles en la bd
//            Rol rol = rolRepository.findById(usuarioDto.getRolId())
//                    .orElseThrow(() -> new EntityNotFoundException(Message.MENSAJE_ERROR_ROL));
//            usuario.setRol(rol);
        usuario.setFechaCreacion(LocalDate.now());
        usuarioRepository.save(usuario);
        return usuario;
            /*return switch (rol.getId()) {
                case 1 -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(String.format(Message.MENSAJE_EXITOSO_REGISTRO, "Admin"));
                case 2 -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(String.format(Message.MENSAJE_EXITOSO_REGISTRO, "User"));
                default -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Rol no encontrado!");
            };     */
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {

        Usuario usuarioActualizado = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id));

        UsuarioDto usuarioDto = new UsuarioDto();

        boolean existeCorreo = usuarioRepository.existsByEmail(usuarioDto.getCorreo());

        String correo = usuarioDto.getCorreo();
        if (correo.isEmpty()) {
            throw new BadRequestException(Message.MENSAJE_ERROR_CORREO_VACIO);
        }

        if (!usuarioActualizado.getCorreo().equals(usuario.getCorreo()) && existeCorreo) {
            throw new BadRequestException(Message.MENSAJE_ERROR_CORREO);
        }

        String telefono = String.valueOf(usuarioDto.getTelefono());

        if (!telefono.equals(usuario.getTelefono()) && telefono.length() != 10) {
            throw new BadRequestException(Message.MENSAJE_ERROR_TELEFONO);
        }

//        if (passwordsEqual(usuarioDto.getPassword(), usuarioDto.getConfirmPassword())) {
//            throw new BadRequestException(Message.MENSAJE_ERROR_PASSWORD);
//        }

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setCorreo(usuarioDto.getCorreo());
        usuario.setTelefono(telefono);
        usuario.setFechaCreacion(LocalDate.now());
//        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        // Verificar que existan los roles en la bd
//                Rol rolActualizado = rolRepository.findById(usuarioDto.getRolId())
//                        .orElseThrow(() -> new EntityNotFoundException(Message.MENSAJE_ERROR_ROL));
//                usuario.setRol(rolActualizado);
//                usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id));

        usuarioRepository.delete(usuario);
    }
}
