package com.api.infrastructure.seguridad.rol.mapper;

import com.api.domain.seguridad.rol.model.Rol;
import com.api.infrastructure.base.mapper.GlobalMapperConfig;
import com.api.infrastructure.seguridad.rol.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface RolMapper {

    @Mapping(target = "id", source = "id")
    Rol toDomain(RolEntity entity);
}
