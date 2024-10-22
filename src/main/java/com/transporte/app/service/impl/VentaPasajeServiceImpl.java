package com.transporte.app.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<VentaPasaje> findAll() {
		// TODO Auto-generated method stub
		return ventapasajeRepository.findAll();
	}
	public String generarNumeroOrden() {
		int numero=0;
		String numeroConcatenado="";
		List<VentaPasaje> ventaPasaje=findAll();
		List<Integer> numeros = new ArrayList<Integer>();
		ventaPasaje.stream().forEach(p->numeros.add(Integer.parseInt(p.getNumero())));
		if(ventaPasaje.isEmpty()) {
			numero=1;
		}
		else {
			numero=numeros.stream().max(Integer::compare).get();
		    numero++;
		}
		if(numero<10) {
			numeroConcatenado="000000000"+String.valueOf(numero);
			
		}else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
			
		}else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
			
		}else if(numero<10000) {
			numeroConcatenado="000000"+String.valueOf(numero);
			
		}
		return numeroConcatenado;
	}

}
