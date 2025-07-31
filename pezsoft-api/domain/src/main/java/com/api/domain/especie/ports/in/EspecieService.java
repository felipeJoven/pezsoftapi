package com.api.domain.especie.ports.in;

import com.api.domain.especie.model.Especie;

import java.util.List;
import java.util.Optional;

public interface EspecieService {

    List<Especie> listarEspecies(String filtro);
    Optional<Especie> listarEspeciePorId(Integer id);
    String agregarEspecie(Especie especie);
    String actualizarEspecie(Integer id, Especie especie);
    String eliminarEspecie(Integer id);
}