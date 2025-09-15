package com.api.domain.produccion.estanque.ports.out;

import com.api.domain.produccion.estanque.model.Estanque;

import java.util.List;
import java.util.Optional;

public interface EstanqueRepository {

    List<Estanque> findAll();
    Optional<Estanque> findById(Integer id);
    List<Estanque> findByFilter(String filtro);
    Estanque save(Estanque estanque);
    void delete(Estanque estanque);
    boolean existeEstanque(String estanque);
    boolean existenCoordenadas( String coordenadas);
}
