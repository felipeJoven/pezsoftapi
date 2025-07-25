package com.api.infrastructure.especie.mapper;

import com.api.domain.especie.model.Especie;
import com.api.infrastructure.especie.entity.EspecieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspecieMapper {

//    EspecieMapper INSTANCE = Mappers.getMapper(EspecieMapper.class);

    Especie toDomain(EspecieEntity especieEntidad);
    EspecieEntity toEntity(Especie especie);
//    List<Especie> toDomainList(List<EspecieEntity> entidades);
//    List<EspecieEntity> toEntityList(List<Especie> models);
}
