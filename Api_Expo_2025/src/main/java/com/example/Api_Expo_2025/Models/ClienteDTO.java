package com.example.Api_Expo_2025.Models;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long idCliente;

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombreCliente;

    @NotBlank(message = "El apellido del cliente no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder los 100 caracteres")
    private String apellidoCliente;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String emailCliente;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres")
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
    private String direccion;

    @Size(min = 10, max = 10, message = "El DUI debe tener exactamente 10 caracteres")
    private String dui;
}