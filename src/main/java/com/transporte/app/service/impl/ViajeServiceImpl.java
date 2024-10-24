package com.transporte.app.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.Viaje;
import com.transporte.app.repository.DestinoRepository;
import com.transporte.app.repository.ViajeRepository;
import com.transporte.app.services.ViajeService;

@Service
public class ViajeServiceImpl implements ViajeService{

	@Autowired
	private ViajeRepository viajeRepository;
	@SuppressWarnings("unused")
	@Autowired
	private DestinoRepository destinoRepository;
	
	public ViajeServiceImpl(ViajeRepository viajeRepositorio) {
		super();
		this.viajeRepository = viajeRepositorio;
	}
	@Override
	public List<Viaje> getAllViajes() {
	
		return viajeRepository.findAll();
	}


	


	@Override
	public Viaje saveViaje(Viaje userEntity) {
		// TODO Auto-generated method stub
		return viajeRepository.save(userEntity);
	}


	@Override
	public Viaje updateViaje(Viaje userEntity) {
		// TODO Auto-generated method stub
		return viajeRepository.save(userEntity);
	}


	@Override
	public void deleteViaje(Integer idBus) {
		// TODO Auto-generated method stub
		viajeRepository.deleteById(idBus);
	}





	@Override
	public Viaje findViajeById(Integer idViaje) {
		// TODO Auto-generated method stub
		return viajeRepository.findById(idViaje).get();
	}







	 public List<Viaje> findViajesByDestinoId(Integer idDestino) {
	        return viajeRepository.findByIdDestino(idDestino); // Asegúrate de que este método exista en tu repositorio
	    }



















	
	

}
