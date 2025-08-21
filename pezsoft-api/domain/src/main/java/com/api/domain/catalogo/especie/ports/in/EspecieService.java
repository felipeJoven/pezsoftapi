package com.api.domain.catalogo.especie.ports.in;

import com.api.domain.catalogo.especie.model.Especie;

import java.util.List;
import java.util.Optional;

public interface EspecieService {

    List<Especie> listarEspecies(String filtro);
    Optional<Especie> listarEspeciePorId(Integer id);
    Especie agregarEspecie(Especie especie);
    Especie actualizarEspecie(Integer id, Especie especie);
    void eliminarEspecie(Integer id);
}