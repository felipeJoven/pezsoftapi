package com.api.domain.especie.ports.out;

import com.api.domain.especie.model.Especie;

import java.util.List;
import java.util.Optional;

public interface EspecieRepository {

    List<Especie> findByEspecie(String filtro);
    List<Especie> findAll();
    Optional<Especie> findById(Integer id);
    void save(Especie especie);
    void delete(Especie especie);
    boolean existsByEspecie(String especie);
}
