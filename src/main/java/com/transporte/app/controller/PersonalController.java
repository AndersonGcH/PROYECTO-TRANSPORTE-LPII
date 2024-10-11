package com.transporte.app.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.transporte.app.entity.Personal;
import com.transporte.app.services.PersonalService;
import com.transporte.app.services.RolService;

@Controller
public class PersonalController {

	@Autowired
	private PersonalService personalService;
    @Autowired
    private RolService rolService;
	
    @GetMapping("/personal")
	public String listPersonal(Model model) {
	    model.addAttribute("personales", personalService.getAllPersonal());
	    return "personal/index";
	}  

	@GetMapping("/personal/new")
	public String createPersonalForm(Model model){
	  	Personal personal = new Personal();
	    model.addAttribute("personal",personal);
	    model.addAttribute("rolList",rolService.getAllRol());
	    return "personal/create";
	}
		
	@PostMapping("/personal")
	public String savePersonal(@ModelAttribute("personal") Personal personal) {
	    personalService.savePersonal(personal);
	    return "redirect:/personal";
	}	
	 
	@GetMapping("/personal/edit/{id}")
	public String editPersonalForm(@PathVariable Integer id, Model model) {
	    Personal st = personalService.findPersonalById(id);
	    model.addAttribute("personal", st);
	    model.addAttribute("rolList",rolService.getAllRol());
	    return "personal/edit";
	}
	
	@PostMapping("/personal/{id}")
	public String updatePersonal(@PathVariable Integer id, 
	    @ModelAttribute("personal") Personal personal, Model model) {
	    Personal existentPersonal = personalService.findPersonalById(id);
	    existentPersonal.setId_personal(id);
	    existentPersonal.setNombre(personal.getNombre());
	    existentPersonal.setApellido(personal.getApellido());
	    existentPersonal.setDni(personal.getDni());
	    existentPersonal.setDireccion(personal.getDireccion());
	    existentPersonal.setEmail(personal.getEmail());
	    existentPersonal.setTelefono(personal.getTelefono());
	    existentPersonal.setIdrol(personal.getIdrol());
	    // guardar el estudiante actualizado
	    personalService.updatePersonal(existentPersonal);
	    return "redirect:/personal";
	}    
	
	@GetMapping("/personal/delete/{id}")
	public String deletePersonal(@PathVariable Integer id) {
	    personalService.deletePersonal(id);
	    return "redirect:/personal";
	}
}
