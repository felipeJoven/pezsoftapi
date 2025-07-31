package com.api.infrastructure.usuario.mapper;

import com.api.domain.usuario.model.Usuario;
import com.api.infrastructure.usuario.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toDomain(UsuarioEntity entity);
    UsuarioEntity toEntity(Usuario usuario);
}
