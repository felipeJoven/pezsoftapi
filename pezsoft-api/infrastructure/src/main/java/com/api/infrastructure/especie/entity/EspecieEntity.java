package com.api.infrastructure.especie.entity;

import com.api.infrastructure.base.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Especie")
@Table(name = "especie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EspecieEntity extends Base {

    @Column(nullable = false)
    private String especie;
}

