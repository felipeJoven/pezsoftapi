package com.api.application.persona.proveedor.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProveedorRequestDto {

    private Integer id;
    private LocalDate fechaCreacion;

    @NotBlank(message = "La razón social es obligatoria!")
    @Size(min = 2, max = 50, message = "La razón social debe tener entre 2 y 50 caracteres")
    private String razonSocial;

    @NotBlank(message = "El número de identificación es obligatorio")
    @Pattern(regexp = "^[0-9-]+$", message = "El número de identificación no puede contener letras!")
    @Size(min = 6, max = 12, message = "El número de identificación debe tener entre 6 y 12 dígitos!")
    private String numeroIdentificacion;

    @NotBlank(message = "El nombre es obligatorio!")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede contener letras!")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio!")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede contener letras!")
    private String apellido;

    @NotBlank(message = "El celular es obligatorio!")
    @Pattern(regexp = "3\\d{9}", message = "El celular debe empezar por 3!")
    @Size(min = 10, max = 10, message = "El celular debe tener exactamente 10 dígitos")
    private String celular;

    @Pattern(regexp = "^$|60\\\\d{8}", message = "El telefono debe empezar por 60!")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio!")
    @Size(min = 2, max = 50, message = "El correo debe tener entre 2 y 50 caracteres")
    @Email(message = "El correo debe tener un formato válido, por ejemplo: usuario@mail.com")
    private String correo;

    @NotBlank(message = "La dirección es obligatoria!")
    @Size(min = 2, max = 50, message = "La dirección debe tener entre 2 y 50 caracteres")
    private String direccion;

    @NotNull(message = "El tipo de identificación es obligatorio!")
    private Integer tipoIdentificacionId;

    @NotNull(message = "El tipo de proveedor es obligatorio!")
    private Integer tipoProveedorId;

}