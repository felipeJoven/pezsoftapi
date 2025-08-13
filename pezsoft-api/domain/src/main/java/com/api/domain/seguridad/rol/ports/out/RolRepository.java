package com.api.domain.seguridad.rol.ports.out;

import com.api.domain.seguridad.rol.model.Rol;

import java.util.List;

public interface RolRepository {

    List<Rol> findAll();
}
