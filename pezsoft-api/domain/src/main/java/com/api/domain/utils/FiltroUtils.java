package com.api.domain.utils;

public class FiltroUtils {

    private FiltroUtils() { }

    public static boolean esFiltroValido(String filtro) {
        return filtro != null && !filtro.trim().isEmpty();
    }
}
