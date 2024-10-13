package com.transporte.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tb_bus")
public class Bus {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_bus")
	    private Integer idBus;

	    @Column(name = "modelo", nullable = false, length = 50)
	    private String modelo;

	    @Column(name = "marca", nullable = false, length = 50)
	    private String marca;

	    @Column(name = "anio", nullable = false)
	    private int anio;

	    @Column(name = "capacidad", nullable = false)
	    private int capacidad;

	    @Column(name = "placa", nullable = false, length = 20)
	    private String placa;

	    public Bus() {
	    }
}
