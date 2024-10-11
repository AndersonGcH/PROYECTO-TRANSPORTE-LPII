package com.transporte.app.services;

import java.util.List;

import com.transporte.app.entity.Personal;

public interface PersonalService {

	public Personal savePersonal (Personal userEntity);
	
	public List<Personal> getAllPersonal();
	
	public Personal updatePersonal (Personal userEntity);
	
	public void deletePersonal (Integer idPersonal);
	
	public Personal findPersonalById(Integer idPersonal);
	
}
