package com.api.infrastructure.base.mapper;

import org.mapstruct.Mapping;

public interface BaseMapper<E, D> {

    @Mapping(source = "id", target = "id")
    D toDomain(E entity);
}
