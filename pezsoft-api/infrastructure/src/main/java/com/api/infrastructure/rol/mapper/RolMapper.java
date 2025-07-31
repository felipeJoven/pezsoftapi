package com.api.infrastructure.rol.mapper;

import com.api.domain.rol.model.Rol;
import com.api.infrastructure.rol.entity.RolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol toDomain(RolEntity entity);
}
