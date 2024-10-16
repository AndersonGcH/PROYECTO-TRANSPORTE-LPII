package com.transporte.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
    @PostMapping("/revision")
    public String saveRevision(@ModelAttribute("revisionBus") RevisionBus revisionBus) {
        revisionBusService.saveRevisionBus(revisionBus);
        return "redirect:/revision";
    }
    
	@GetMapping("/revision/edit/{id}")
	public String editRevisionForm(@PathVariable Integer id, Model model) {
		 RevisionBus st = revisionBusService.findRevisionBusById(id);
		 model.addAttribute("revision", st);
		    model.addAttribute("buses",busService.getAllBuses());

		 return "revision/edit";
	}
    
	@PostMapping("/revision/{id}")
	public String updateRevision(@PathVariable Integer id, 
	    @ModelAttribute("revision") RevisionBus revision, Model model) {
	    RevisionBus existentRevision = revisionBusService.findRevisionBusById(id);
	    existentRevision.setRevisionId(id);
	    existentRevision.setBus(revision.getBus());
	    existentRevision.setFechaRevision(revision.getFechaRevision());
	    existentRevision.setTipoRevision(revision.getTipoRevision());
	    existentRevision.setResultado(revision.getResultado());
	    existentRevision.setObservaciones(revision.getObservaciones());
	    // guardar el estudiante actualizado
	    revisionBusService.updateRevisionBus(existentRevision);
	    return "redirect:/revision";
	}   
    
	@GetMapping("/revision/delete/{id}")
	public String deleteRevision(@PathVariable Integer id) {
		revisionBusService.deleteRevisionBus(id);
	    return "redirect:/revision";
	}
}
