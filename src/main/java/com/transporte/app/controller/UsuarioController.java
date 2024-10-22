package com.transporte.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.transporte.app.entity.Rol;
import com.transporte.app.entity.Usuario;
import com.transporte.app.services.DestinoService;
import com.transporte.app.services.RolService;
import com.transporte.app.services.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private DestinoService destinoService;

    @GetMapping("/cliente")
    public String mostrarDestinos(Model model) {
        model.addAttribute("destinos", destinoService.getAllDestinos());
        return "cliente/home"; // Update the return statement to the correct template path
    }


    // Redirigir a la página home del cliente
    @GetMapping("/")
    public String clientehome() {
        return "redirect:/cliente"; // Redirigir a la página de destinos públicos
    }
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        // Logic to show the login page
        return "login"; // Return the view for the login page
    }
    @PostMapping("/login")
    public String iniciarSesion(Model model, @ModelAttribute("usuario") Usuario usuario, HttpSession session) {
        String pagina = ""; // Default redirect to client home page
        Usuario usuarioAutenticado = usuarioService.login(usuario);

        if (usuarioAutenticado != null) {
            // Save the authenticated user in session
            session.setAttribute("idusuario", usuarioAutenticado.getId());

            // Check the user's role
            if ("Administrador".equals(usuarioAutenticado.getRol().getDescripcion())) {
                model.addAttribute("usuarios", usuarioService.getAllUsuario());
                model.addAttribute("rolList", rolService.getAllRol());
                return "redirect:/administrador/index";
                // Administrators also see the client home page by default
            }
            // Both admin and client go to the same home page
        } else {
            // Show error message in case of failed authentication
            model.addAttribute("error", "Usuario o contraseña incorrecta");
            pagina = "error"; // Redirect to an error page
        }

        return pagina;
    }


    
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
    @GetMapping("/registro")
    public String create() {
    	
    	
        return "registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario) {
      
        // Busca el rol de cliente (con id = 2) en la base de datos
        Rol rolCliente = rolService.findById(2); // rolService es un servicio que debe estar disponible para obtener los roles
        
        // Asigna el rol de cliente al usuario
        usuario.setRol(rolCliente);
        
        // Guarda el usuario (lógica de guardado no mostrada)
        usuarioService.saveUsuarioCliente(usuario); // Asegúrate de que tu usuarioService esté definido

        return "redirect:/";
    }
}