package com.api.domain.catalogo.especie.ports.out;

import com.api.domain.catalogo.especie.model.Especie;

import java.util.List;
import java.util.Optional;

public interface EspecieRepository {

    List<Especie> findAll();
    Optional<Especie> findById(Integer id);
    List<Especie> findByFilter(String filtro);
    Especie save(Especie especie);
    void delete(Especie especie);
    boolean existsByEspecie(String especie);
}
