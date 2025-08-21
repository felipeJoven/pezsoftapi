package com.api.infrastructure.seguridad.usuario.mapper;

import com.api.application.seguridad.usuario.dto.UsuarioDto;
import com.api.domain.seguridad.usuario.model.Usuario;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.seguridad.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface UsuarioMapper {

    Usuario toDomain(UsuarioEntity entity);
    Usuario dtoToDomain(UsuarioDto dto);
    UsuarioEntity toEntity(Usuario usuario);
}
