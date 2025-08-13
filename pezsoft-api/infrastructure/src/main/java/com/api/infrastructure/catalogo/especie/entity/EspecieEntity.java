package com.api.infrastructure.catalogo.especie.entity;

import com.api.infrastructure.base.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "especie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecieEntity extends Base {

    @Column(nullable = false)
    private String especie;
}

