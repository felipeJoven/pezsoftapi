package com.api.infrastructure.catalogo.especie.entity;

import com.api.infrastructure.base.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "El nombre de la especie es obligatorio!")
    @Size(min = 2, max = 30, message = "El nombre de la especie debe tener entre 2 y 30 caracteres!")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$",
            message = "El nombre de la especie solo puede contener letras!")
    private String especie;
}

