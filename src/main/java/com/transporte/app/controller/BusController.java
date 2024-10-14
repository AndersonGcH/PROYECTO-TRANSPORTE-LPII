package com.transporte.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.transporte.app.entity.Bus;
import com.transporte.app.entity.Personal;
import com.transporte.app.services.BusService;

@Controller
public class BusController {
	
	@Autowired
	BusService busService;
	
    @GetMapping("/bus")
	public String listBus(Model model) {
	    model.addAttribute("buses", busService.getAllBuses());
	    return "bus/index";
	}  
    
	@GetMapping("/bus/new")
	public String createBusForm(Model model){
	  	Bus bus = new Bus();
	    model.addAttribute("bus",bus);
	    return "bus/create";
	}
	
	@PostMapping("/bus")
	public String saveBus(@ModelAttribute("bus") Bus bus) {
	    busService.saveBus(bus);
	    return "redirect:/bus";
	}	
	
	@GetMapping("/bus/edit/{id}")
	public String editBusForm(@PathVariable Integer id, Model model) {
		 Bus st = busService.findBusById(id);
		 model.addAttribute("bus", st);
		 return "bus/edit";
	}
		
		@PostMapping("/bus/{id}")
		public String updateBus(@PathVariable Integer id, 
		    @ModelAttribute("bus") Bus bus, Model model) {
		    Bus existentBus = busService.findBusById(id);
		    existentBus.setIdBus(id);
		    existentBus.setModelo(bus.getModelo());
		    existentBus.setMarca(bus.getMarca());
		    existentBus.setAnio(bus.getAnio());
		    existentBus.setCapacidad(bus.getCapacidad());
		    existentBus.setPlaca(bus.getPlaca());
		    // guardar el estudiante actualizado
		    busService.updateBus(existentBus);
		    return "redirect:/bus";
		}    
		
		@GetMapping("/bus/delete/{id}")
		public String deleteBus(@PathVariable Integer id) {
			busService.deleteBus(id);
		    return "redirect:/bus";
		}
		
}
