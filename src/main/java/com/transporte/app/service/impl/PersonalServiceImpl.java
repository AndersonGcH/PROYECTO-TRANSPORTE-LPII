package com.transporte.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.Personal;
import com.transporte.app.repository.PersonalRepository;
import com.transporte.app.services.PersonalService;

@Service
public class PersonalServiceImpl implements PersonalService{

	@Autowired
	PersonalRepository personalRepository;

	@Override
	public Personal savePersonal(Personal userEntity) {
		// TODO Auto-generated method stub
		return personalRepository.save(userEntity);
	}

	@Override
	public List<Personal> getAllPersonal() {
		// TODO Auto-generated method stub
		return personalRepository.findAll();
	}

	@Override
	public Personal updatePersonal(Personal userEntity) {
		// TODO Auto-generated method stub
		return personalRepository.save(userEntity);
	}

	@Override
	public void deletePersonal(Integer idPersonal) {
		// TODO Auto-generated method stub
		personalRepository.deleteById(idPersonal);
	}

	@Override
	public Personal findPersonalById(Integer idPersonal) {
		// TODO Auto-generated method stub
		return personalRepository.findById(idPersonal).get();
	}
	
	
}
