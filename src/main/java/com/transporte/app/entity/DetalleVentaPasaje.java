package com.transporte.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleVentaPasaje {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idDetalle;
	    
	    @ManyToOne
	    @JoinColumn(name = "id_venta", nullable = false)
	    private VentaPasaje ventaPasaje;  // Relación con VentaPasaje
	    
	    @ManyToOne
	    @JoinColumn(name = "id_viaje", nullable = false)
	    private Viaje viaje;  // Relación con Viaje
	    
	    @Column(name = "asiento", nullable = false)
	    private int asiento;
	    
	    @Column(name = "precio", nullable = false)
	    private double precio; 
}
