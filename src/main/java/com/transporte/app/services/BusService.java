package com.transporte.app.services;

import java.util.List;

import com.transporte.app.entity.Bus;

public interface BusService {
	public Bus saveBus (Bus userEntity);
	
	public List<Bus> getAllBuses();
	
	public Bus updateBus (Bus userEntity);
	
	public void deleteBus (Integer idBus);
	
	public Bus findBusById(Integer idBus);
}
