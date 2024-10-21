package com.transporte.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.Pasajero;
import com.transporte.app.repository.PasajeroRepository;
import com.transporte.app.services.PasajeroService;

@Service
public class PasajeroServiceImpl implements PasajeroService{

	
	@Autowired
	private PasajeroRepository pasajeroRepository;
	
	@Override
	public Pasajero findById(Integer id) {
		// TODO Auto-generated method stub
		return pasajeroRepository.findById(id).get();
	}

}
