package com.api.application.catalogo.especie.service;

import com.api.domain.catalogo.especie.model.Especie;
import com.api.domain.catalogo.especie.ports.in.EspecieService;
import com.api.domain.catalogo.especie.ports.out.EspecieRepository;
import com.api.application.utils.MessageUtils;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.utils.FiltroUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecieServiceImpl implements EspecieService {

    private static final Logger logger = LoggerFactory.getLogger(EspecieServiceImpl.class);
    private final EspecieRepository especieRepository;

    @Override
    public List<Especie> listarEspecies(String filtro) {

        List<Especie> especies;

        if (FiltroUtils.esFiltroValido(filtro)) {
            especies = especieRepository.findByFilter(filtro);
            logger.info("Especies filtradas: {}", especies.size());

            if (especies.isEmpty()) {
                logger.warn(MessageUtils.NO_ENCONTRADO + "especies con filtro: {}", filtro);
                throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "especies!");
            }
        } else {
            especies = especieRepository.findAll();

            if (especies.isEmpty()) {
                logger.warn(MessageUtils.NO_EXISTE, "especies");
            } else {
                logger.info("Especies encontradas: {}", especies.size());
            }
        }

        return especies;
    }

    @Override
    public Optional<Especie> listarEspeciePorId(Integer id) {

        Optional<Especie> especieId = especieRepository.findById(id);

        if (especieId.isEmpty()) {
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return especieId;
    }

    @Override
    @Transactional
    public Especie agregarEspecie(Especie especie) {

        boolean existeEspecie = especieRepository.existsByEspecie(especie.getEspecie());

        if (existeEspecie) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "la especie");
        }

        especie.setFechaCreacion(LocalDate.now());
        return especieRepository.save(especie);

    }

    @Override
    @Transactional
    public Especie actualizarEspecie(Integer id, Especie especieNueva) {

        boolean existeEspecie = especieRepository.existsByEspecie(especieNueva.getEspecie());

        Especie especieActual = especieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        boolean cambioEspecie = !especieNueva.getEspecie().equalsIgnoreCase(especieActual.getEspecie());

        if (cambioEspecie && existeEspecie) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "esta especie");
        }

        especieActual.setEspecie(especieNueva.getEspecie());
        return especieRepository.save(especieActual);
    }

    @Override
    @Transactional
    public void eliminarEspecie(Integer id) {

        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        especieRepository.delete(especie);
    }
}