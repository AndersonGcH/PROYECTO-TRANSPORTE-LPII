package com.transporte.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.Rol;
import com.transporte.app.repository.RolRepository;
import com.transporte.app.services.RolService;

@Service
public class RolServiceImpl implements RolService{
	@Autowired
	RolRepository rolRepository;

	@Override
	public List<Rol> getAllRol() {
		// TODO Auto-generated method stub
		return rolRepository.findAll();
	}

	@Override
	public Rol findById(Integer id) {
		// TODO Auto-generated method stub
		return rolRepository.findById(id).get();
	}
}
