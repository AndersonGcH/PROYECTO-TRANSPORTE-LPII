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
@Table(name = "tb_destino")
public class Destino {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_destino")
	    private Integer idDestino;

	    @Column(name = "nombre_des", length = 100, nullable = false)
	    private String nombre;

	    @Column(name = "imagen", length = 255) // Asegúrate de que el tamaño sea adecuado
	    private String imagen; // Aquí se almacena la URL o ruta de la imagen

	    // Constructor vacío
	    public Destino() {
	    }
}