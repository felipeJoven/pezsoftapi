package com.api.infrastructure.base.repository;

import java.io.Serializable;

import com.api.infrastructure.base.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRespositoryJpa<E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
}
