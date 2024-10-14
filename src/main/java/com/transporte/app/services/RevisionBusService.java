package com.transporte.app.services;

import java.util.List;

import com.transporte.app.entity.RevisionBus;

public interface RevisionBusService {
	public RevisionBus saveRevisionBus (RevisionBus userEntity);
	
	public List<RevisionBus> getAllRevisionBuses();
	
	public RevisionBus updateRevisionBus (RevisionBus userEntity);
	
	public void deleteRevisionBus (Integer idRevision);
	
	public RevisionBus findRevisionBusById(Integer idRevision);
}
