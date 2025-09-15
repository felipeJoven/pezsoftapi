package com.api.application.utils;

public class Message {
    
    public static final String MENSAJE_ERROR_LISTAR = "No se encontraron ";
    public static final String MENSAJE_ERROR_LISTAR_ID = "No se encontró el Id: ";
    public static final String MENSAJE_ERROR_OBTENER_ENTIDAD = "No se encontró %s!";
    public static final String MENSAJE_ERROR_EXISTE = "Ya existe %s en la base de datos!";
    public static final String MENSAJE_ERROR_NO_ID = "No se generó el Id de la entidad ";

    public static final String MENSAJE_ERROR_CORREO_VACIO = "Debe ingresar un correo electrónico!";
    public static final String MENSAJE_ERROR_TELEFONO = "El número de teléfono debe tener exactamente 10 dígitos";
    public static final String MENSAJE_ERROR_LOGIN = "Credenciales invalidas";
    public static final String MENSAJE_ERROR_CORREO = "El correo electrónico ya existe, intenta con otro";
    public static final String MENSAJE_ERROR_PASSWORD = "Las contraseñas no coinciden";
    public static final String MENSAJE_ERROR_SOLICITUD = "Error al procesar la solicitud: ";
    public static final String MENSAJE_ERROR_ROL = "El rol no existe";
    public static final String MENSAJE_ERROR_ROL_ADMIN = "El rol Admin no se encontró en la base de datos!";
    public static final String MENSAJE_ERROR_SIN_USUARIOS = "No existe ningún usuario en la base de datos!";
    public static final String MENSAJE_ERROR_OBLIGATORIO = "Este campo no puede ir vacío!";

    public static final String MENSAJE_ERROR_PECES = "El número de peces %s no puede ser mayor a los existentes en el lote!";
    public static final String MENSAJE_ERROR_PECES_0 = "El valor de peces %s debe ser mayor a 0 para crear este registro!";
    public static final String MENSAJE_ERROR_AUTORIZADO = "Usuario con rol '%s' no autorizado ";
    public static final String MENSAJE_ERROR_AUTORIZADO_ADMIN = "Solamente puede acceder un %s";

}
