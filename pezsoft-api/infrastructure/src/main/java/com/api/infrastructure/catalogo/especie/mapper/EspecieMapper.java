package com.api.infrastructure.catalogo.especie.mapper;

import com.api.domain.catalogo.especie.model.Especie;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.catalogo.especie.entity.EspecieEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface EspecieMapper {

    Especie toDomain(EspecieEntity entity);
    EspecieEntity toEntity(Especie especie);
}
