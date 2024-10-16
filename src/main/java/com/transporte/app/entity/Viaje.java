package com.transporte.app.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_viaje")
public class Viaje {
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_viaje")
	    private Integer idViaje;

	    @ManyToOne
	    @JoinColumn(name = "id_bus", nullable = false)
	    private Bus idBus;

	    @ManyToOne
	    @JoinColumn(name = "id_destino", nullable = false)
	    private Destino idDestino;

	    @Column(name = "fech_sal", nullable = false)
	    private LocalDate  fechaSalida;

	    @Column(name = "fech_lle", nullable = false)
	    private LocalDate  fechaLlegada;

	    @Column(name = "incidencias", length = 40)
	    private String incidencias;

	    @Column(name = "precio", nullable = false)
	    private BigDecimal precio;

	    // Constructor vacío
	    public Viaje() {}

}
