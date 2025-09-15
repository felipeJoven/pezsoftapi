package com.api.infrastructure.produccion.estanque.mapper;

import com.api.application.produccion.estanque.dto.EstanqueRequestDto;
import com.api.application.produccion.estanque.dto.EstanqueResponseDto;
import com.api.domain.produccion.estanque.model.Estanque;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.produccion.estanque.entity.EstanqueEntity;
import com.api.infrastructure.produccion.estanque.tipoestanque.mapper.TipoEstanqueMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class, uses = {TipoEstanqueMapper.class})
public interface EstanqueMapper {

    Estanque toDomain(EstanqueEntity entity);

    EstanqueEntity toEntity(Estanque estanque);

    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "tipoEstanque.id", source = "tipoEstanqueId")
    Estanque requestToDomain(EstanqueRequestDto dto);

    @Mapping(target = "tipoEstanque", source = "tipoEstanque.tipoEstanque")
    EstanqueResponseDto domainToResponse(Estanque domain);
}
