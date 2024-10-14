package com.transporte.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.Bus;
import com.transporte.app.repository.BusRepository;
import com.transporte.app.services.BusService;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	BusRepository busRepository;

	@Override
	public Bus saveBus(Bus userEntity) {
		// TODO Auto-generated method stub
		return busRepository.save(userEntity);
	}

	@Override
	public List<Bus> getAllBuses() {
		// TODO Auto-generated method stub
		return busRepository.findAll();
	}

	@Override
	public Bus updateBus(Bus userEntity) {
		// TODO Auto-generated method stub
		return busRepository.save(userEntity);
	}

	@Override
	public void deleteBus(Integer idBus) {
		// TODO Auto-generated method stub
		busRepository.deleteById(idBus);
	}

	@Override
	public Bus findBusById(Integer idBus) {
		// TODO Auto-generated method stub
		return busRepository.findById(idBus).get();
	}
	
	
}
