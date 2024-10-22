package com.transporte.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(Model model, @ModelAttribute("usuario") Usuario usuario) {
        String pagina = "";
        Usuario usuarioAutenticado = usuarioService.login(usuario);

        if (usuarioAutenticado != null) {
            if ("Administrador".equals(usuarioAutenticado.getRol().getDescripcion())) {
                model.addAttribute("usuarios", usuarioService.getAllUsuario());
                model.addAttribute("rolList", rolService.getAllRol());
                pagina = "redirect:/administrador/index"; 
            } else {
                model.addAttribute("destinos", destinoService.getAllDestinos());
                pagina = "redirect:/cliente"; 
            }
        } else {
            pagina = "error";
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

    @GetMapping("/usuario/new")
    public String createUsuarioForm(Model model) {
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