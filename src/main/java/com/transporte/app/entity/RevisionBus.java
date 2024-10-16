package com.transporte.app.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "tb_revision")
@Getter
@Setter
public class RevisionBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revision_id")
    private Integer revisionId;

    @ManyToOne
    @JoinColumn(name = "id_bus", nullable = false)
    private Bus bus;

    @Column(name = "fecha_revision", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRevision;

    @Column(name = "tipo_revision", length = 50, nullable = false)
    private String tipoRevision;

    @Column(name = "resultado", length = 50, nullable = false)
    private String resultado;

    @Column(name = "observaciones", length = 50)
    private String observaciones;
    
    public RevisionBus() {
    }
}
