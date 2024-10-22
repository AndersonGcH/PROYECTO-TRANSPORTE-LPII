package com.transporte.app.services;

import java.util.List;
import java.util.Optional;

import com.transporte.app.entity.Bus;
import com.transporte.app.entity.Destino;
import com.transporte.app.entity.Viaje;

public interface ViajeService {
	public List<Viaje> getAllViajes();
	
	public Viaje findViajeById(Integer idViaje);
	
    public Viaje saveViaje (Viaje userEntity);
	
	
	public Viaje updateViaje (Viaje userEntity);
	
	public void deleteViaje (Integer idBus);

	public List<Viaje> findViajesByDestinoId(Integer idDestino);

	
}
