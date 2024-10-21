package com.transporte.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.VentaPasaje;
import com.transporte.app.repository.VentaPasajeRepository;
import com.transporte.app.services.VentaPasajeService;

@Service
public class VentaPasajeServiceImpl implements VentaPasajeService{

	@Autowired
	private VentaPasajeRepository ventapasajeRepository;
	
	@Override
	public VentaPasaje save(VentaPasaje venta) {
		// TODO Auto-generated method stub
		return ventapasajeRepository.save(venta);
	}

}
