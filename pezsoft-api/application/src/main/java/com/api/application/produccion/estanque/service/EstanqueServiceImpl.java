package com.api.application.produccion.estanque.service;

import com.api.application.utils.MessageUtils;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.exception.IdNotGeneratedException;
import com.api.domain.produccion.estanque.model.Estanque;
import com.api.domain.produccion.estanque.ports.in.EstanqueService;
import com.api.domain.produccion.estanque.ports.out.EstanqueRepository;
import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import com.api.domain.produccion.estanque.tipoestanque.ports.out.TipoEstanqueRepository;
import com.api.domain.utils.FiltroUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstanqueServiceImpl implements EstanqueService {

    private static final Logger logger = LoggerFactory.getLogger(EstanqueServiceImpl.class);
    private final EstanqueRepository estanqueRepository;
    private final TipoEstanqueRepository tipoEstanqueRepository;

    @Override
    public List<Estanque> listarEstanque(String filtro) {

        List<Estanque> estanques;

        if (FiltroUtils.esFiltroValido(filtro)) {
            estanques = estanqueRepository.findByFilter(filtro);
            logger.info("Estanques filtrados: {} ", estanques.size());

            if (estanques.isEmpty()) {
                logger.warn(MessageUtils.NO_ENCONTRADO + "estanques con filtro: {}", filtro);
                throw new NotFoundException(MessageUtils.NO_ENCONTRADO + "estanques!");
            }
        } else {
            estanques = estanqueRepository.findAll();
            if (estanques.isEmpty()) {
                logger.warn(MessageUtils.NO_EXISTE, "estanques");
            } else {
                logger.info("Estanques encontrados: {} ", estanques.size());
            }
        }

        return estanques;
    }

    @Override
    public Optional<Estanque> listarEstanquePorId(Integer id) {

        Optional<Estanque> estanqueId = estanqueRepository.findById(id);

        if (estanqueId.isEmpty()) {
            throw new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id);
        }

        return estanqueId;
    }

    @Override
    @Transactional
    public Estanque agregarEstanque(Estanque estanque) {

        boolean existeEstanque = estanqueRepository.existeEstanque(estanque.getEstanque());
        boolean existeCoordenadas = estanqueRepository.existenCoordenadas(estanque.getCoordenadas());

        if (existeEstanque && existeCoordenadas) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el estanque y las coordenadas");
        } else if (existeEstanque) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "este estanque");
        } else if (existeCoordenadas) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "estas coordenadas");
        }

        TipoEstanque tipoEstanque = tipoEstanqueRepository.findById(estanque.getTipoEstanque().getId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, " tipo de estanque"));

        estanque.setEstado(false);
        estanque.setTipoEstanque(tipoEstanque);
        estanque.setFechaCreacion(LocalDate.now());

        Estanque guardarEstanque = estanqueRepository.save(estanque);

        if (guardarEstanque.getId() == null) {
            throw new IdNotGeneratedException(MessageUtils.ID_NO_GENERADO + "estanque");
        }

        return guardarEstanque;
    }

    @Override
    @Transactional
    public Estanque actualizarEstanque(Integer id, Estanque estanqueNuevo) {

        boolean existeEstanque = estanqueRepository.existeEstanque(estanqueNuevo.getEstanque());
        boolean existeCoordenadas = estanqueRepository.existenCoordenadas(estanqueNuevo.getCoordenadas());

        Estanque estanqueActual = estanqueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        boolean cambioEstanque = !estanqueNuevo.getEstanque().equalsIgnoreCase(estanqueActual.getEstanque());
        boolean cambioCoordenadas = !estanqueNuevo.getCoordenadas().equalsIgnoreCase(estanqueActual.getCoordenadas());

        if (existeEstanque && cambioEstanque && existeCoordenadas && cambioCoordenadas) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "el estanque y las coordenadas");
        } else if (cambioEstanque && existeEstanque) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "este estanque");
        } else if (cambioCoordenadas && existeCoordenadas) {
            throw new ConflictException(MessageUtils.YA_EXISTE, "estas coordenadas");
        }

        TipoEstanque tipoEstanque = tipoEstanqueRepository.findById(estanqueNuevo.getTipoEstanque().getId())
                .orElseThrow(() -> new NotFoundException(MessageUtils.ENTIDAD_NO_ENCONTRADA, "tipo de estanque"));

        estanqueNuevo.setTipoEstanque(tipoEstanque);

        BeanUtils.copyProperties(estanqueNuevo, estanqueActual, "id", "fechaCreacion", "estado");

        return estanqueRepository.save(estanqueActual);
    }

    @Override
    @Transactional
    public void eliminarEstanque(Integer id) {

        Estanque estanque = estanqueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageUtils.ID_NO_ENCONTRADO + id));

        estanqueRepository.delete(estanque);
    }
}
