package com.transporte.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.DetalleVentaPasaje;
import com.transporte.app.repository.DetalleVentaPasajeRepository;
import com.transporte.app.services.DetalleVentaPasajeService;

@Service
public class DetalleVentaPasajeImpl implements DetalleVentaPasajeService{

	@Autowired
	private DetalleVentaPasajeRepository detalleventaPasajeRepository;
	
	@Override
	public DetalleVentaPasaje save(DetalleVentaPasaje detalle) {
		// TODO Auto-generated method stub
		return detalleventaPasajeRepository.save(detalle);
	}

}
