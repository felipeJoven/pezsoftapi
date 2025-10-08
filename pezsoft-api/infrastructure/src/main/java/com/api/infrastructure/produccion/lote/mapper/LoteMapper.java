package com.api.infrastructure.produccion.lote.mapper;

import com.api.application.produccion.lote.dto.LoteRequestDto;
import com.api.application.produccion.lote.dto.LoteResponseDto;
import com.api.domain.produccion.lote.model.Lote;
import com.api.infrastructure.catalogo.especie.mapper.EspecieMapper;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.persona.proveedor.mapper.ProveedorMapper;
import com.api.infrastructure.produccion.estanque.mapper.EstanqueMapper;
import com.api.infrastructure.produccion.lote.entity.LoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class, uses = {EspecieMapper.class, ProveedorMapper.class, EstanqueMapper.class})
public interface LoteMapper {

    Lote toDomain(LoteEntity entity);

    LoteEntity toEntity(Lote lote);

    @Mapping(target = "especie.id", source = "especieId")
    @Mapping(target = "proveedor.id", source = "proveedorId")
    @Mapping(target = "estanque.id", source = "estanqueId")
    Lote requestToDomain(LoteRequestDto dto);

    @Mapping(target = "especieId", source = "especie.id")
    @Mapping(target = "especie", source = "especie.especie")
    @Mapping(target = "proveedorId", source = "proveedor.id")
    @Mapping(target = "proveedor", source = "proveedor.razonSocial")
    @Mapping(target = "estanqueId", source = "estanque.id")
    @Mapping(target = "estanque", source = "estanque.estanque")
    LoteResponseDto domainToResponse(Lote lote);
}
