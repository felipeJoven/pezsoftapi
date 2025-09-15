package com.api.application.persona.proveedor.tipoproveedor.service;

import com.api.application.utils.Message;
import com.api.domain.exception.NotFoundException;
import com.api.domain.persona.proveedor.tipoproveedor.model.TipoProveedor;
import com.api.domain.persona.proveedor.tipoproveedor.ports.in.TipoProveedorService;
import com.api.domain.persona.proveedor.tipoproveedor.ports.out.TipoProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoProveedorServiceImpl implements TipoProveedorService {

    private final TipoProveedorRepository tipoProveedorRepository;

    @Override
    public List<TipoProveedor> verTipos() {

        List<TipoProveedor> tipos = tipoProveedorRepository.buscarTodos();

        if (tipos.isEmpty()) {
            throw new NotFoundException(Message.MENSAJE_ERROR_LISTAR + "tipos de proveedores!");
        }

        return tipos;
    }
}
