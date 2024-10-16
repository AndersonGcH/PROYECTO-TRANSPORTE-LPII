package com.transporte.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transporte.app.entity.Destino;
import com.transporte.app.entity.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer>{
    @Query("SELECT v FROM Viaje v WHERE v.idDestino.idDestino = :idDestino") // Asegúrate de que la propiedad sea la correcta
    List<Viaje> findByIdDestino(Integer idDestino);
}
