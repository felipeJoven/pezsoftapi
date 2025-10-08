package com.api.application.seguridad.rol.service;

import com.api.application.utils.MessageUtils;
import com.api.domain.exception.NotFoundException;
import com.api.domain.seguridad.rol.model.Rol;
import com.api.domain.seguridad.rol.ports.in.RolService;
import com.api.domain.seguridad.rol.ports.out.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public List<Rol> listarRoles() {

        List<Rol> roles = rolRepository.findAll();

        if (roles.isEmpty()) {
            throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "roles!");
        }
        
        return roles;
    }
}
