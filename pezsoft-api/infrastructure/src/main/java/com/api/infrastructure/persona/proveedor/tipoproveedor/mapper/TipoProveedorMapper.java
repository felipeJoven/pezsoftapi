package com.api.infrastructure.persona.proveedor.tipoproveedor.mapper;

import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.infrastructure.common.GlobalMapperConfig;
import com.api.infrastructure.persona.proveedor.tipoproveedor.entity.TipoProveedorEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface TipoProveedorMapper {

    TipoProveedor toDomain(TipoProveedorEntity entity);
}
