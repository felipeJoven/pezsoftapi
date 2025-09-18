package com.api.application.catalogo.especie.service;

import com.api.domain.catalogo.especie.model.Especie;
import com.api.domain.catalogo.especie.ports.in.EspecieService;
import com.api.domain.catalogo.especie.ports.out.EspecieRepository;
import com.api.application.utils.Message;
import com.api.domain.exception.BadRequestException;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecieServiceImpl implements EspecieService {

    private final EspecieRepository especieRepository;

    @Override
    public List<Especie> listarEspecies(String filtro) {

        List<Especie> especies = (filtro != null && !filtro.isEmpty())
                ? especieRepository.findByFilter(filtro)
                : especieRepository.findAll();

        if (especies.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR + "especies!");
        }

        return especies;
    }

    @Override
    public Optional<Especie> listarEspeciePorId(Integer id) {

        Optional<Especie> especieId = especieRepository.findById(id);

        if (especieId.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id);
        }

        return especieId;
    }

    @Override
    public Especie agregarEspecie(Especie especie) {

        boolean existeEspecie = especieRepository.existsByEspecie(especie.getEspecie());

        if (existeEspecie) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "la especie");
        }

        especie.setFechaCreacion(LocalDate.now());
        return especieRepository.save(especie);

    }

    @Override
    @Transactional
    public Especie actualizarEspecie(Integer id, Especie especie) {

        Especie especieActualizada = especieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id));

        boolean existeEspecie = especieRepository.existsByEspecie(especie.getEspecie());

        if (!especie.getEspecie().equals(especieActualizada.getEspecie()) && existeEspecie) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "esta especie");
        }

        especieActualizada.setEspecie(especie.getEspecie());
        return especieRepository.save(especieActualizada);
    }

    @Override
    @Transactional
    public void eliminarEspecie(Integer id) {

        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id));

        especieRepository.delete(especie);
    }
}