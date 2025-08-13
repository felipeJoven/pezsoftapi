package com.api.infrastructure.seguridad.usuario.mapper;

import com.api.domain.seguridad.usuario.model.Usuario;
import com.api.infrastructure.base.mapper.BaseMapper;
import com.api.infrastructure.base.mapper.GlobalMapperConfig;
import com.api.infrastructure.seguridad.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface UsuarioMapper extends BaseMapper<UsuarioEntity, Usuario> {

    Usuario toDomain(UsuarioEntity entity);
    UsuarioEntity toEntity(Usuario usuario);
}
