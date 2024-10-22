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

import jakarta.annotation.PostConstruct;
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

    private final Long temporaryUserId = 1L; // ID del usuario temporal
    private final String temporaryUserName = "Usuario Temporal"; // Nombre del usuario temporal

    @PostConstruct
    public void init() {
        // Inicializa el usuario temporal al inicio
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute("idusuario", temporaryUserId);
            session.setAttribute("nombreUsuario", temporaryUserName); // Almacena el nombre del usuario temporal
        }
    }

    private HttpSession getSession() {
        // Método para obtener la sesión actual
        // Este método no se puede utilizar directamente en el controlador
        // se debe manejar en cada método donde se necesite la sesión
        return null; // Deja esto aquí, se manejará de otra manera
    }

    @GetMapping("/cliente")
    public String mostrarDestinos(Model model) {
        model.addAttribute("destinos", destinoService.getAllDestinos());
        return "cliente/home"; // Retorna la plantilla correcta
    }

    // Redirigir a la página home del cliente
    @GetMapping("/")
    public String clientehome() {
        return "redirect:/cliente"; // Redirigir a la página de destinos públicos
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login"; // Retorna la vista para la página de inicio de sesión
    }

    @PostMapping("/login")
    public String iniciarSesion(Model model, @ModelAttribute("usuario") Usuario usuario, HttpSession session) {
        Usuario usuarioAutenticado = usuarioService.login(usuario);

        if (usuarioAutenticado != null) {
            // Guarda el usuario autenticado en la sesión
            session.setAttribute("idusuario", usuarioAutenticado.getId());
            session.setAttribute("nombreUsuario", usuarioAutenticado.getNombres());

            // Redirige según el rol del usuario
            if ("Administrador".equals(usuarioAutenticado.getRol().getDescripcion())) {
                return "redirect:/administrador/index";
            }
            return "redirect:/cliente"; // Redirigir a la página de cliente
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrecta");
            return "login"; // Volver a la página de login con error
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalida la sesión
        }
        return "redirect:/"; // Redirigir a la página de inicio
    }

    @GetMapping("/registro")
    public String create() {
        return "registro"; // Retornar la vista de registro
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        Rol rolCliente = rolService.findById(2); // Obtiene el rol de cliente
        usuario.setRol(rolCliente); // Asigna el rol al usuario
        usuarioService.saveUsuarioCliente(usuario); // Guarda el usuario
        return "redirect:/"; // Redirigir a la página de inicio
    }

    // Lógica para manejar las compras
    @GetMapping("/comprar")
    public String comprar(HttpSession session, Model model) {
        Long idUsuario = (Long) session.getAttribute("idusuario");

        if (idUsuario == null || idUsuario.equals(temporaryUserId)) {
            // Si el usuario no está autenticado o es el temporal, redirigir a login
            model.addAttribute("error", "Debes registrarte o iniciar sesión para realizar una compra.");
            return "login"; // Redirigir a la página de login
        }

        // Aquí va la lógica de compra si el usuario es válido
        return "compra"; // Redirigir a la página de compra
    }
}
