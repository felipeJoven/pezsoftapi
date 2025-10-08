package com.api.domain.produccion.lote.ports.in;

import com.api.domain.produccion.lote.model.Lote;

import java.util.List;
import java.util.Optional;

public interface LoteService {

     List<Lote> verLotes(String filtro);
     Optional<Lote> verLotePorId(Integer id);
     Lote agregarLote(Lote lote);
     Lote actualizarLote(Integer id, Lote lote);
     void eliminarLote(Integer id);
}
