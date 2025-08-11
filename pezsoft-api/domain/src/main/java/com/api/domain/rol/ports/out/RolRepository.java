package com.api.domain.rol.ports.out;

import com.api.domain.rol.model.Rol;

import java.util.List;

public interface RolRepository {

    List<Rol> findAll();
}
