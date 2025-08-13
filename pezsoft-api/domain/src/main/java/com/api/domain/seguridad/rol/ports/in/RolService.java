package com.api.domain.seguridad.rol.ports.in;


import com.api.domain.seguridad.rol.model.Rol;

import java.util.List;

public interface RolService {

    List<Rol> listarRoles();
}
