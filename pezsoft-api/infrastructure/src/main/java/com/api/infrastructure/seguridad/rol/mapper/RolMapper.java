package com.api.infrastructure.seguridad.rol.mapper;

import com.api.domain.seguridad.rol.model.Rol;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.seguridad.rol.entity.RolEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface RolMapper {

    Rol toDomain(RolEntity entity);
}
