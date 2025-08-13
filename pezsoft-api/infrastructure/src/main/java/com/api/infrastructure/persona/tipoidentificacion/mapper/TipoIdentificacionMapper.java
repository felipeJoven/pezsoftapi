package com.api.infrastructure.persona.tipoidentificacion.mapper;

import com.api.domain.persona.tipoidentificacion.model.TipoIdentificacion;
import com.api.infrastructure.base.mapper.BaseMapper;
import com.api.infrastructure.base.mapper.GlobalMapperConfig;
import com.api.infrastructure.persona.tipoidentificacion.entity.TipoIdentificacionEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface TipoIdentificacionMapper extends BaseMapper<TipoIdentificacionEntity, TipoIdentificacion> {

    TipoIdentificacion toDomain(TipoIdentificacionEntity entity);
}
