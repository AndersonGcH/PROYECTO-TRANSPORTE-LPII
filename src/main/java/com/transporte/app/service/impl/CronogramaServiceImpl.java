package com.transporte.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transporte.app.entity.Cronograma;
import com.transporte.app.repository.CronogramaRepository;
import com.transporte.app.services.CronogramaService;

@Service
public class CronogramaServiceImpl implements CronogramaService{


	@Autowired
	private CronogramaRepository cronogramaRepository;
	
	@Override
	public List<Cronograma> getAllCronogramas() {
		return cronogramaRepository.findAll();
	}

}
