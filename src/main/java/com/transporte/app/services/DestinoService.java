package com.transporte.app.services;

import java.util.List;
import java.util.Optional;

import com.transporte.app.entity.Bus;
import com.transporte.app.entity.Destino;

public interface DestinoService {
	public List<Destino> getAllDestinos();

	Optional<Destino> findDestinoById(Integer id);
	
	public Destino findById(Integer idDestino);

}
