package com.api.infrastructure.produccion.modulo.tipoestanque.mapper;

import com.api.domain.produccion.modulo.tipoestanque.model.TipoEstanque;
import com.api.infrastructure.base.mapper.GlobalMapperConfig;
import com.api.infrastructure.produccion.modulo.tipoestanque.entity.TipoEstanqueEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface TipoEstanqueMapper {

    TipoEstanque toDomain(TipoEstanqueEntity entity);
}
