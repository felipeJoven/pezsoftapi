package com.api.infrastructure.produccion.lote.entity;

import com.api.infrastructure.base.entity.Base;
import com.api.infrastructure.catalogo.especie.entity.EspecieEntity;
import com.api.infrastructure.persona.proveedor.entity.ProveedorEntity;
import com.api.infrastructure.produccion.estanque.entity.EstanqueEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "lote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteEntity extends Base {

    @Column(nullable = false)
    private String lote;

    @Column(name = "fecha_siembra", nullable = false)
    private LocalDate fechaSiembra;

    @Column(name = "peces_iniciales", nullable = false)
    private int pecesIniciales;

    @Column(name = "numero_peces", nullable = false)
    private int  numeroPeces;

    @ManyToOne(optional = false)
    @JoinColumn(name = "especie_id", nullable = false)
    private EspecieEntity especie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private ProveedorEntity proveedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estanque_id", nullable = false)
    private EstanqueEntity estanque;
}
