package com.api.application.seguridad.menu;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItem {

    private String parent;
    private String label;
    private String routerLink;
}
