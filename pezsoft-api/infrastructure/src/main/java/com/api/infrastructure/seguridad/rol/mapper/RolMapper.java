package com.api.infrastructure.seguridad.rol.mapper;

import com.api.domain.seguridad.rol.model.Rol;
import com.api.infrastructure.base.mapper.BaseMapper;
import com.api.infrastructure.base.mapper.GlobalMapperConfig;
import com.api.infrastructure.seguridad.rol.entity.RolEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface RolMapper extends BaseMapper<RolEntity, Rol> {

    Rol toDomain(RolEntity entity);
}
