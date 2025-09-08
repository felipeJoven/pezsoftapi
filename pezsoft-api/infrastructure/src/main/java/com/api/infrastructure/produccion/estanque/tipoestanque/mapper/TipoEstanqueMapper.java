package com.api.infrastructure.produccion.estanque.tipoestanque.mapper;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.produccion.estanque.tipoestanque.entity.TipoEstanqueEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface TipoEstanqueMapper {

    TipoEstanque toDomain(TipoEstanqueEntity entity);
}
