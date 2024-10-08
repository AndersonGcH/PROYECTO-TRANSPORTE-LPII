package com.transporte.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.transporte.app.entity.Usuario;
import com.transporte.app.services.RolService;
import com.transporte.app.services.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolService;

	@GetMapping("/")
    public String login(){
        return "login";
    }
		
	@PostMapping("/login")
	public String iniciarSesion(Model model,@ModelAttribute("usuario") Usuario usuario) {
		String pagina="";	
		boolean band=usuarioService.login(usuario);
		System.out.println("bandd--> "+band);
		
		if(band==true) {
			   model.addAttribute("usuarios", usuarioService.getAllUsuario());
			   model.addAttribute("rolList", rolService.getAllRol());			
			   pagina =  "usuario/index";					
		}else {
			pagina="error";
		}		
		return pagina;
	}
	
	 @GetMapping("/usuario/new")
	 public String createUsuarioForm(Model model){
		 // este objeto Usuario almacenara los valores 
		 Usuario usuario = new Usuario();
	       
	     model.addAttribute("usuario", usuario);
	     model.addAttribute("rolList", rolService.getAllRol());
	     return "usuario/create";	     
	 }
		 
	 @PostMapping("/usuario")
	 public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
		 usuarioService.saveUsuario(usuario);
	     return "redirect:/usuario";
	 }
	 
     @GetMapping("/usuario")
	 public String listUsuarios(Model model) {
	     model.addAttribute("usuarios", usuarioService.getAllUsuario());
	     model.addAttribute("rolList", rolService.getAllRol());	        
	     return "usuario/index";
	 }
}
