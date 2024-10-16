package com.transporte.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transporte.app.entity.Destino;
import com.transporte.app.entity.Viaje;

public interface DestinoRepository extends JpaRepository<Destino, Integer>{

}
