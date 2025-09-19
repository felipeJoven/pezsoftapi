package com.api.application.produccion.estanque.service;

import com.api.application.utils.Message;
import com.api.domain.exception.ConflictException;
import com.api.domain.exception.NotFoundException;
import com.api.domain.exception.IdNotGeneratedException;
import com.api.domain.produccion.estanque.model.Estanque;
import com.api.domain.produccion.estanque.ports.in.EstanqueService;
import com.api.domain.produccion.estanque.ports.out.EstanqueRepository;
import com.api.domain.produccion.estanque.tipoestanque.model.TipoEstanque;
import com.api.domain.produccion.estanque.tipoestanque.ports.out.TipoEstanqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstanqueServiceImpl implements EstanqueService {

    private final EstanqueRepository estanqueRepository;
    private final TipoEstanqueRepository tipoEstanqueRepository;

    @Override
    public List<Estanque> listarEstanque(String filtro) {

        List<Estanque> estanques = (filtro != null && !filtro.isEmpty())
                ? estanqueRepository.findByFilter(filtro)
                : estanqueRepository.findAll();

        if (estanques.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR + "estanques!");
        }

        return estanques;
    }

    @Override
    public Optional<Estanque> listarEstanquePorId(Integer id) {

        Optional<Estanque> estanqueId = estanqueRepository.findById(id);

        if (estanqueId.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id);
        }

        return estanqueId;
    }

    @Override
    @Transactional
    public Estanque agregarEstanque(Estanque estanque) {

        boolean existeEstanque = estanqueRepository.existeEstanque(estanque.getEstanque());
        boolean existeCoordenadas = estanqueRepository.existenCoordenadas(estanque.getCoordenadas());

        if (existeEstanque && existeCoordenadas) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "el estanque y las coordenadas");
        } else if (existeEstanque) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "este estanque");
        } else if (existeCoordenadas) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "estas coordenadas");
        }

        TipoEstanque tipoEstanque = tipoEstanqueRepository.findById(estanque.getTipoEstanque().getId())
                .orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_OBTENER_ENTIDAD, " tipo de estanque"));

        estanque.setEstado(false);
        estanque.setTipoEstanque(tipoEstanque);
        estanque.setFechaCreacion(LocalDate.now());

        Estanque guardarEstanque = estanqueRepository.save(estanque);

        if (guardarEstanque.getId() == null) {
            throw new IdNotGeneratedException(Message.MENSAJE_ERROR_NO_ID + "estanque");
        }

        return guardarEstanque;
    }

    @Override
    @Transactional
    public Estanque actualizarEstanque(Integer id, Estanque estanqueNuevo) {

        boolean existeEstanque = estanqueRepository.existeEstanque(estanqueNuevo.getEstanque());
        boolean existeCoordenadas = estanqueRepository.existenCoordenadas(estanqueNuevo.getCoordenadas());

        Estanque estanqueActual = estanqueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id));

        boolean cambioEstanque = !estanqueNuevo.getEstanque().equalsIgnoreCase(estanqueActual.getEstanque());
        boolean cambioCoordenadas = !estanqueNuevo.getCoordenadas().equalsIgnoreCase(estanqueActual.getCoordenadas());

        if (existeEstanque && cambioEstanque && existeCoordenadas && cambioCoordenadas) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "el estanque y las coordenadas");
        } else if (cambioEstanque && existeEstanque) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "este estanque");
        } else if (cambioCoordenadas && existeCoordenadas) {
            throw new ConflictException(Message.MENSAJE_ERROR_EXISTE, "estas coordenadas");
        }

        TipoEstanque tipoEstanque = tipoEstanqueRepository.findById(estanqueNuevo.getTipoEstanque().getId())
                .orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_OBTENER_ENTIDAD, "tipo de estanque"));

        estanqueNuevo.setTipoEstanque(tipoEstanque);

        BeanUtils.copyProperties(estanqueNuevo, estanqueActual, "id", "fechaCreacion", "estado");

        return estanqueRepository.save(estanqueActual);
    }

    @Override
    @Transactional
    public void eliminarEstanque(Integer id) {

        Estanque estanque = estanqueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Message.MENSAJE_ERROR_LISTAR_ID + id));

        estanqueRepository.delete(estanque);
    }
}
