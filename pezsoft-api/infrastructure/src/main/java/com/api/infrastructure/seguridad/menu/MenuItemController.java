package com.api.infrastructure.seguridad.menu;

import com.api.application.seguridad.menu.MenuItem;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
@CrossOrigin(origins = "*")
public class MenuItemController {

    @GetMapping("/administrador")
    public List<MenuItem> obtenerMenuAdmin() {
        return List.of(
                new MenuItem("Usuario", "Integrantes", "/seguridad/usuario"),
                new MenuItem("Catálogo", "Especies", "/catalogo/especie"),
                new MenuItem("Persona", "Proveedores", "/persona/proveedor"),
                new MenuItem("Producción", "Estanques", "/produccion/estanque"),
                new MenuItem("Producción", "Lotes", "/produccion/lote")
        );
    }

    @GetMapping("/usuario")
    public List<MenuItem> obtenerMenuUsuario() {
        return List.of(
                new MenuItem("Catálogo", "Especies", "/catalogo/especie"),
                new MenuItem("Persona", "Proveedores", "/persona/proveedor"),
                new MenuItem("Producción", "Estanques", "/produccion/estanque"),
                new MenuItem("Producción", "Lotes", "/produccion/lote")
        );
    }
}
