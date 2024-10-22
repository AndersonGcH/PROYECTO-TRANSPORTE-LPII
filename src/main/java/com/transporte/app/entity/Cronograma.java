package com.transporte.app.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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

@Table(name = "tb_cronograma")
@Entity
@Getter
@Setter
public class Cronograma {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id_cronograma;

	    @ManyToOne
	    @JoinColumn(name = "id_viaje", nullable = false)
	    private Viaje viaje;
	    
	    @Column(name = "fecha", nullable = false)
	    private LocalDate  fechaSalida;
	    
	    @Column(name = "hora_salida", nullable = false)
	    private LocalTime hora_salida;

	    @Column(name = "hora_llegada", nullable = false)
	    private LocalTime hora_llegada;

	    @ManyToOne
	    @JoinColumn(name = "id_bus", nullable = false)
	    private Bus bus;
}
