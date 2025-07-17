package com.example.Api_Expo_2025.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CLIENTES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EntityCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    @SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1)
    @Column(name = "IDCLIENTE")
    private Long id;

    @Column(name = "NOMBRECLIENTE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APELLIDOCLIENTE", nullable = false, length = 100)
    private String apellido;

    @Column(name = "EMAILCLIENTE", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "TELEFONO", nullable = false, length = 20)
    private String telefono;

    @Column(name = "DIRECCION", nullable = false, length = 200)
    private String direccion;

    @Column(name = "DUI", unique = true, length = 10)
    private String dui;
}
