package com.api.domain.catalogo.especie.ports.out;

import com.api.domain.catalogo.especie.model.Especie;

import java.util.List;
import java.util.Optional;

public interface EspecieRepository {

    List<Especie> findByEspecie(String filtro);
    List<Especie> findAll();
    Optional<Especie> findById(Integer id);
    Especie save(Especie especie);
    void delete(Especie especie);
    boolean existsByEspecie(String especie);
}
