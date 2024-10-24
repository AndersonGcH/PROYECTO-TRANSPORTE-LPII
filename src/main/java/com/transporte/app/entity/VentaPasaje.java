package com.transporte.app.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VentaPasaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVenta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)  // Cambiado a nullable = true
    private Usuario usuario;  // Relación con Usuario
    
    
    @Column(name = "fecha_venta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;
    
    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "estado", length = 50, nullable = true)  // Cambiado a nullable = true
    private String estado;

    // Relación con DetalleVentaPasaje
    @OneToMany(mappedBy = "ventaPasaje", cascade = CascadeType.ALL)
    private List<DetalleVentaPasaje> detalles;
    
    @Column(name = "numero", nullable = false)
    private String numero; // Atributo de tipo String
}

