package com.transporte.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.RevisionBus;
import com.transporte.app.repository.RevisionBusRepository;
import com.transporte.app.services.RevisionBusService;

@Service
public class RevisionBusServiceImpl implements RevisionBusService{

	@Autowired
	RevisionBusRepository revisionBusRepository;
	@Override
	public RevisionBus saveRevisionBus(RevisionBus userEntity) {
		// TODO Auto-generated method stub
		return revisionBusRepository.save(userEntity);
	}

	@Override
	public List<RevisionBus> getAllRevisionBuses() {
		// TODO Auto-generated method stub
		return revisionBusRepository.findAll();
	}

	@Override
	public RevisionBus updateRevisionBus(RevisionBus userEntity) {
		// TODO Auto-generated method stub
		return revisionBusRepository.save(userEntity);
	}

	@Override
	public void deleteRevisionBus(Integer idRevision) {
		// TODO Auto-generated method stub
		revisionBusRepository.deleteById(idRevision);
	}

	@Override
	public RevisionBus findRevisionBusById(Integer idRevision) {
		// TODO Auto-generated method stub
		return revisionBusRepository.findById(idRevision).get();
	}

}
