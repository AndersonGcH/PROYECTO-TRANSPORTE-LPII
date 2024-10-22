package com.transporte.app.services;

import java.util.List;

import com.transporte.app.entity.VentaPasaje;

public interface VentaPasajeService {

	public List<VentaPasaje> findAll();
	public VentaPasaje save(VentaPasaje venta);
	public String generarNumeroOrden();
	
}
