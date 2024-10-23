package com.transporte.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.transporte.app.services.CronogramaService;

@Controller
public class CronogramaController {

	@Autowired
	CronogramaService cronogramaService; 
	
    @GetMapping("/cronograma")
	public String listBus(Model model) {
	    model.addAttribute("cronogramas", cronogramaService.getAllCronogramas());
	    return "cliente/cronograma";
	} 
}
