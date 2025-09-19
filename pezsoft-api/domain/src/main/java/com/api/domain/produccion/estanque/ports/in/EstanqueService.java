package com.api.domain.produccion.estanque.ports.in;

import com.api.domain.produccion.estanque.model.Estanque;

import java.util.List;
import java.util.Optional;

public interface EstanqueService {

    List<Estanque> listarEstanque(String filtro);
    Optional<Estanque> listarEstanquePorId(Integer id);
    Estanque agregarEstanque(Estanque estanque);
    Estanque actualizarEstanque(Integer id, Estanque estanqueNuevo);
    void eliminarEstanque(Integer id);
}
