package com.transporte.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.Bus;
import com.transporte.app.entity.Destino;
import com.transporte.app.repository.DestinoRepository;
import com.transporte.app.services.DestinoService;

@Service
public class DestinoServiceImpl implements DestinoService{

	@Autowired
	private DestinoRepository destinoRepository;
	
	
	@Override
	public List<Destino> getAllDestinos() {
		// TODO Auto-generated method stub
		return destinoRepository.findAll();
	}


	@Override
	public Optional<Destino> findDestinoById(Integer id) {
		// TODO Auto-generated method stub
		return destinoRepository.findById(id);
	}


	public Destino findById(Integer idDestino) {
        Optional<Destino> destinoOptional = destinoRepository.findById(idDestino);
        if (destinoOptional.isPresent()) {
            return destinoOptional.get();
        } else {
            // Manejar el caso donde no se encuentra el destino
            throw new RuntimeException("Destino no encontrado para el ID: " + idDestino);
        }
    }


}
