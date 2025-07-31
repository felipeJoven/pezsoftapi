package com.api.infrastructure.especie.mapper;

import com.api.domain.especie.model.Especie;
import com.api.infrastructure.especie.entity.EspecieEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EspecieMapper {

    Especie toDomain(EspecieEntity entity);
    EspecieEntity toEntity(Especie especie);
}
