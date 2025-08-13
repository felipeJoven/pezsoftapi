package com.api.application.seguridad.rol.service;

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
        return rolRepository.findAll();
    }
}
