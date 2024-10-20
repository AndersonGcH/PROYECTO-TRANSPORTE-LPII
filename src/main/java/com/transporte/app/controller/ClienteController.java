package com.transporte.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.transporte.app.entity.Destino;
import com.transporte.app.entity.Viaje;
import com.transporte.app.services.DestinoService;
import com.transporte.app.services.ViajeService;

@Controller
public class ClienteController {
    @Autowired
    private DestinoService destinoService;
    @Autowired
    private ViajeService viajeService;

    @GetMapping("/cliente")
    public String cliente(Model model) {
        List<Destino> destinos = destinoService.getAllDestinos();
        model.addAttribute("destinos", destinos);
        return "cliente/home";
	}

    @GetMapping("/destinohome/{idDestino}")
    public String listarViajesPorDestino(@PathVariable Integer idDestino, Model model) {
        List<Viaje> viajes = viajeService.findViajesByDestinoId(idDestino);
        Destino destino = destinoService.findById(idDestino); // Asegúrate de tener un servicio para obtener el destino por ID
        model.addAttribute("viajes", viajes);
        model.addAttribute("nombreDestino", destino.getNombre());
        return "cliente/destinohome"; 
    }

    @GetMapping("/cliente/home")
    public String clienteHome() {
        return "cliente/home";
}
}