package com.api.infrastructure.produccion.estanque.tipoestanque.mapper;

import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.produccion.estanque.tipoestanque.entity.TipoEstanqueEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface TipoEstanqueMapper {

    TipoEstanque toDomain(TipoEstanqueEntity entity);

    default TipoEstanque map(Integer id) {
        if (id == null) return null;
        TipoEstanque tipo = new TipoEstanque();
        tipo.setId(id);
        return tipo;
    }

    default Integer map(TipoEstanque tipo) {
        return tipo != null ? tipo.getId() : null;
    }
}
