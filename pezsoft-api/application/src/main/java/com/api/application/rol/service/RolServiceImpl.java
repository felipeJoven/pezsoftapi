package com.api.application.rol.service;

import com.api.domain.rol.model.Rol;
import com.api.domain.rol.ports.in.RolService;
import com.api.domain.rol.ports.out.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private RolRepository rolRepository;

    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}
