package com.transporte.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.transporte.app.entity.Bus;
import com.transporte.app.entity.Destino;
import com.transporte.app.entity.Viaje;
import com.transporte.app.services.BusService;
import com.transporte.app.services.DestinoService;
import com.transporte.app.services.ViajeService;


@Controller
public class ViajeController {
	
	@Autowired
	private ViajeService viajeService;
	@Autowired
	private DestinoService destinoService;
	@Autowired
	private BusService busService;
	
	
	
    @GetMapping("/viaje")
	public String listViajes(Model model) {
	    model.addAttribute("viajes", viajeService.getAllViajes());
	    return "viaje/index";
	}  
	@GetMapping("/viaje/new")
	public String createViajeForm(Model model){
	  	Viaje viaje = new Viaje();
	    model.addAttribute("viaje",viaje);
	    model.addAttribute("destinos", destinoService.getAllDestinos());
        model.addAttribute("buses", busService.getAllBuses());
	    return "viaje/create";
	}
	
	@PostMapping("/viaje")
	public String saveViaje(@ModelAttribute("viaje") Viaje viaje) {
	    viajeService.saveViaje(viaje);
	    return "redirect:/viaje";
	}	
	
	@GetMapping("/viaje/edit/{id}")
	public String editViajeForm(@PathVariable Integer id, Model model) {
		 Viaje st = viajeService.findViajeById(id);
		 model.addAttribute("viaje", st);
		 model.addAttribute("destinos", destinoService.getAllDestinos());
	     model.addAttribute("buses", busService.getAllBuses());
		 return "viaje/edit";
	}
		
		@PostMapping("/viaje/{id}")
		public String updateViaje(@PathVariable Integer id, 
		    @ModelAttribute("viaje") Viaje viaje, Model model) {
		    Viaje existentViaje = viajeService.findViajeById(id);
		    existentViaje.setIdViaje(id);
		    existentViaje.setIdBus(viaje.getIdBus());
		    existentViaje.setIdDestino(viaje.getIdDestino());
		    existentViaje.setFechaSalida(viaje.getFechaSalida());
		    existentViaje.setFechaLlegada(viaje.getFechaSalida());
		    existentViaje.setIncidencias(viaje.getIncidencias());
		    
		    // guardar el estudiante actualizado
		    viajeService.updateViaje(existentViaje);
		    return "redirect:/viaje";
		}    
		
		@GetMapping("/viaje/delete/{id}")
		public String deleteViaje(@PathVariable Integer id) {
			viajeService.deleteViaje(id);
		    return "redirect:/viaje";
		}

}
