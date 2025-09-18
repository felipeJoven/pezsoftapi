package com.api.infrastructure.persona.proveedor.mapper;

import com.api.application.persona.proveedor.dto.ProveedorRequestDto;
import com.api.application.persona.proveedor.dto.ProveedorResponseDto;
import com.api.domain.persona.proveedor.model.Proveedor;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.persona.proveedor.entity.ProveedorEntity;
import com.api.infrastructure.persona.proveedor.tipoproveedor.mapper.TipoProveedorMapper;
import com.api.infrastructure.persona.tipoidentificacion.mapper.TipoIdentificacionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class, uses = {TipoIdentificacionMapper.class, TipoProveedorMapper.class})
public interface ProveedorMapper {

    Proveedor toDomain(ProveedorEntity entity);

    ProveedorEntity toEntity(Proveedor proveedor);

    @Mapping(target = "tipoIdentificacion.id", source = "tipoIdentificacionId")
    @Mapping(target = "tipoProveedor.id", source = "tipoProveedorId")
    Proveedor requestToDomain(ProveedorRequestDto dto);

    @Mapping(target = "tipoIdentificacionId", source = "tipoIdentificacion.id")
    @Mapping(target = "tipoIdentificacion", source = "tipoIdentificacion.tipoIdentificacion")
    @Mapping(target = "tipoProveedorId", source = "tipoProveedor.id")
    @Mapping(target = "tipoProveedor", source = "tipoProveedor.tipoProveedor")
    ProveedorResponseDto domainToResponse(Proveedor entity);
}
