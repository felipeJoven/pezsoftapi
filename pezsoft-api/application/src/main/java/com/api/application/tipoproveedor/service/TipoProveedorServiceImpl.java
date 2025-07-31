/*
package com.api.application.tipoproveedor.service;

import com.api.domain.tipoproveedor.model.TipoProveedor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoProveedorServiceImpl implements TipoProveedorService {

    private TipoProveedorRepository tipoProveedorRepository;

    @Override
    public List<TipoProveedor> verTipoProveedores() {
            List<TipoProveedor> tipoProveedores = tipoProveedorRepository.findAll();
            if (!tipoProveedores.isEmpty()) {
                return ResponseEntity.ok(tipoProveedores);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Message.MENSAJE_ERROR_VER + "tipo de proveedores!");
            }

    }
}
*/
