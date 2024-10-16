package com.transporte.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pasajero {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPasajero;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "dni", nullable = false, length = 20)
    private String dni;
    
    @Column(name = "telefono", length = 20)
    private String telefono;

}
