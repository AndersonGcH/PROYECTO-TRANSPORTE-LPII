package com.transporte.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transporte.app.entity.Usuario;
import com.transporte.app.entity.VentaPasaje;

@Repository
public interface VentaPasajeRepository extends JpaRepository<VentaPasaje,Integer>{

	List<VentaPasaje> findByUsuarioId(Usuario usuario);
}
