package com.transporte.app.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "detalle_venta_pasaje")
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
	   
	    
	    @Column(name = "precio", nullable = false)
	    private double precio; 
	    
	    @Column(name = "cantidad", nullable = false)
	    private int cantidad;

	    @Column(name = "total", nullable = false)
	    private double total;
	    
	
}