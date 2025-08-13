package com.api.infrastructure.persona.proveedor.tipoproveedor.mapper;

import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.infrastructure.base.mapper.BaseMapper;
import com.api.infrastructure.base.mapper.GlobalMapperConfig;
import com.api.infrastructure.persona.proveedor.tipoproveedor.entity.TipoProveedorEntity;
import org.mapstruct.Mapper;

@Mapper(config = GlobalMapperConfig.class)
public interface TipoProveedorMapper extends BaseMapper<TipoProveedorEntity, TipoProveedor> {

    TipoProveedor toDomain(TipoProveedorEntity entity);
}
