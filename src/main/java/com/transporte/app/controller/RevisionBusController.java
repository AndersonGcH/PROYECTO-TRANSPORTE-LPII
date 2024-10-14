package com.transporte.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.transporte.app.entity.Personal;
import com.transporte.app.entity.RevisionBus;
import com.transporte.app.services.BusService;
import com.transporte.app.services.RevisionBusService;


@Controller
public class RevisionBusController {
	
	@Autowired
	RevisionBusService revisionBusService;
	
	@Autowired
	BusService busService;
	
    @GetMapping("/revision")
	public String listRevisionBus(Model model) {
	    model.addAttribute("revisiones", revisionBusService.getAllRevisionBuses());
	    return "revision/index";
	}  
    
	@GetMapping("/revision/new")
	public String createRevisionBusForm(Model model){
	  	RevisionBus revisionBus = new RevisionBus();
	    model.addAttribute("revisiones",revisionBus);
	    model.addAttribute("buses",busService.getAllBuses());
	    return "revision/create";
	}
}
